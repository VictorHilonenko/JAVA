package spring.scheduler.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import spring.scheduler.dto.AppointmentDTO;
import spring.scheduler.entity.Appointment;
import spring.scheduler.entity.Role;
import spring.scheduler.entity.ServiceType;
import spring.scheduler.entity.User;
import spring.scheduler.exceptions.ErrorsEnum;
import spring.scheduler.exceptions.ExtendedRuntimeException;
import spring.scheduler.repository.AppointmentRepository;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private UserService userService;
    private EmailMessageService emailMessageService;
    
    @Value("${app.WORK_TIME_STARTS}")
    private int WORK_TIME_STARTS;
	
    @Value("${app.WORK_TIME_ENDS}")
    private int WORK_TIME_ENDS;
    
    @Value("${app.NO_IDLE_MASTER_RESTRICTION}")
    private String strNoIdleMasterRestriction;
    
    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserService userService, EmailMessageService emailMessageService) {
		this.appointmentRepository = appointmentRepository;
		this.userService = userService;
		this.emailMessageService = emailMessageService;
	}

	public Optional<Appointment> findById(Long id) {
        try {
	        return appointmentRepository.findById(id);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }

	public List<AppointmentDTO> getAllAppointmentsDTO(String strDateStart, String strDateEnd) {
    	String email = userService.getCurrentUserName();
    	Role role = userService.getCurrentRole();
    	
        try {
			return appointmentRepository.findByPeriod(strDateStart, strDateEnd)
		    		.stream()
					.map(a -> appointmentToDTO(a, email, role))
					.collect(Collectors.toList());
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}
	
	public boolean validateAppointmentDTOForAdd(String strDate, String strTime, String strServiceType) {
		Byte time = Byte.parseByte(strTime);
    	if((time < WORK_TIME_STARTS) || (time > WORK_TIME_ENDS)) {
			return false;
    	}
    	//TODO also check if it is not in past, now it's in frond end
    	
        try {
        	strServiceType = ServiceType.valueOf(strServiceType).name();
        } catch (IllegalArgumentException e) {
    		return false;
        }
        
    	return true;
	}
	
    public boolean addAppointment(AppointmentDTO appointmentDTO) {
    	HashMap<String, String> map = appointmentDTO.getMap();  
    	String date = map.get("date");
    	String time = map.get("time");
    	String service_type = map.get("serviceType");
		
		if(!validateAppointmentDTOForAdd(date, time, service_type)) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.WRONG_DATA_PASSED);
		}
    	
    	User user = userService.getCurrentUser()
				   .orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.ACCESS_DENIED));
	
    	try {
    		appointmentRepository.reserveTime(user.getEmail(), date, time, service_type);
		} catch (org.springframework.dao.DataIntegrityViolationException eIntegrity) {
			if(strNoIdleMasterRestriction.equalsIgnoreCase(eIntegrity.getCause().getCause().getMessage())) {
		    	throw new ExtendedRuntimeException(ErrorsEnum.NO_IDLE_MASTER, eIntegrity);
			} else {
		    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, eIntegrity);
			}
		} catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    	
    	return true;
    }
    
    //actually in this business logic only one field can be changed when we update an Appointment, it's "serviceProvided" 
    public boolean updateServiceProvided(AppointmentDTO appointmentDTO) {
    	HashMap<String, String> map = appointmentDTO.getMap();  
    	
		Long id;
    	try {
			id = Long.parseLong(map.get("id"));
		} catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.WRONG_DATA_PASSED, e);
		}
    	
		Appointment appointment = appointmentRepository.findById(id)
													   .orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.WRONG_DATA_PASSED));
		
		boolean serviceProvidedNewValue = "true".equals(map.get("serviceProvided"));
    	
		if(!changesPermittedAndNeeded(appointment, serviceProvidedNewValue)) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.ACCESS_DENIED);
		}
    	
		appointment.setServiceProvided(serviceProvidedNewValue);
    	try {
    		appointmentRepository.save(appointment);
		} catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    	
    	//we are here only when the serviceProvided is set to "true", 
    	//so we need to send email to a customer for receiving feedback: 
		addFeedbackEmailToQueue(appointment);
		
    	return true;
    }
    
	private boolean changesPermittedAndNeeded(Appointment appointment, boolean serviceProvidedNewValue) {
		if(!appointment.getMaster().getEmail().equals(userService.getCurrentUserName())) {
			//"hacker" detected...
			//TODO maybe block that user, now we only don't save changes to DB
			return false;
		}
		
		if(!serviceProvidedNewValue) {
			//somebody tries to set this value to "false", that's out of logic 
			return false;
		}
		
		return serviceProvidedNewValue != appointment.getServiceProvided();
	}

	public void addFeedbackEmailToQueue(Appointment appointment) {
		String quickAccessCode = UUID.randomUUID().toString();
		String subject = "leave feedback, please";
		String serverAddress = "http://localhost:8989"; //TODO props
		String message = "please, leave your feedback here: "+serverAddress+"/feedbacks/"+appointment.getId()+"/"+quickAccessCode;
		String email = appointment.getCustomer().getEmail();
		
		emailMessageService.addEmailToQueue(email, subject, message, quickAccessCode);
		
		emailMessageService.sendUnsentEmailsFromQueue();
	}
	
    public AppointmentDTO appointmentToDTO(Appointment appointment, String email, Role role) {
    	AppointmentDTO appointmentDTO = new AppointmentDTO();
    	HashMap<String, String> fieldsMap = appointmentDTO.getMap();
    	
    	setFieldsAndRightsToDTOAccordingToPolicy(fieldsMap, appointment, email, role);
    	
    	return appointmentDTO;
    }
    
    //fields with prefixes "rights_" are used in frontend to provide rights for separate roles
    //values can be: "H" - hidden, "R" - read only, "W" - write 
    private void setFieldsAndRightsToDTOAccordingToPolicy(HashMap<String, String> fieldsMap, Appointment appointment, String email, Role role) {
    	//common fields:
    	fieldsMap.put("id", appointment.getId().toString());
    	fieldsMap.put("rights_id", "H");
    	fieldsMap.put("date", appointment.getAppointmentDate().toString());
    	fieldsMap.put("rights_date", "R");
    	fieldsMap.put("time", appointment.getAppointmentTime().toString());
    	fieldsMap.put("rights_time", "R");
    	fieldsMap.put("serviceType", appointment.getServiceType().name());
    	fieldsMap.put("rights_serviceType", "R");
    	//
    	
    	if(role.equals(Role.ROLE_USER)) {
    		addFieldsForUser(fieldsMap, appointment, email);
    	} else if(role.equals(Role.ROLE_MASTER)) {
    		addFieldsForMaster(fieldsMap, appointment, email);
    	} else if(role.equals(Role.ROLE_ADMIN)) {
    		addFieldsForAdmin(fieldsMap, appointment);
    	}
	}

	private void addFieldsForUser(HashMap<String, String> fieldsMap, Appointment appointment, String email) {
		if(email.equals(appointment.getCustomer().getEmail())) {
	    	fieldsMap.put("customer_name", appointment.getCustomer().getFirstName());
	    	fieldsMap.put("rights_customer_name", "R");
	    	
	    	if(appointment.getServiceProvided()) {
		    	fieldsMap.put("serviceProvided", appointment.getServiceProvided().toString());
		    	fieldsMap.put("rights_serviceProvided", "R");
	    	}
		}
	}
	
	private void addFieldsForMaster(HashMap<String, String> fieldsMap, Appointment appointment, String email) {
		if(email.equals(appointment.getMaster().getEmail())) {
	    	fieldsMap.put("customer_name", appointment.getCustomer().getFirstName());
	    	fieldsMap.put("rights_customer_name", "R");
	    	fieldsMap.put("master_name", appointment.getMaster().getFirstName());
	    	fieldsMap.put("rights_master_name", "R");
	    	
	    	fieldsMap.put("serviceProvided", appointment.getServiceProvided().toString());
    		if(appointment.getServiceProvided()) {
    	    	fieldsMap.put("rights_serviceProvided", "R");
    		} else {
    	    	fieldsMap.put("rights_serviceProvided", "W");
    		}
		} else if(email.equals(appointment.getCustomer().getEmail())) {
			//that's possible when a master was a Customer for some service
    		if(appointment.getServiceProvided()) {
    	    	fieldsMap.put("serviceProvided", appointment.getServiceProvided().toString());
    	    	fieldsMap.put("rights_serviceProvided", "R");
    		}
		}
	}
	
	private void addFieldsForAdmin(HashMap<String, String> fieldsMap, Appointment appointment) {
    	fieldsMap.put("customer_email", appointment.getCustomer().getEmail());
    	fieldsMap.put("rights_customer_email", "H");
    	
    	fieldsMap.put("customer_name", userService.getFullNamePlusTelNumber(appointment.getCustomer()));
    	fieldsMap.put("rights_customer_name", "R");
    	
    	fieldsMap.put("master_email", appointment.getMaster().getEmail());
    	fieldsMap.put("rights_master_email", "H");
    	
    	fieldsMap.put("master_name", userService.getFullNamePlusTelNumber(appointment.getMaster()));
    	fieldsMap.put("rights_master_name", "R");
    	
    	fieldsMap.put("serviceProvided", appointment.getServiceProvided().toString());
    	fieldsMap.put("rights_serviceProvided", "R");
	}
}