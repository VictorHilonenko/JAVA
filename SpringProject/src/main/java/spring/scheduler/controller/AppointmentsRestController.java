package spring.scheduler.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.scheduler.dto.AppointmentDTO;
import spring.scheduler.exceptions.ErrorsEnum;
import spring.scheduler.exceptions.ExtendedRuntimeException;
import spring.scheduler.service.AppointmentService;

@RestController()
public class AppointmentsRestController {
    private AppointmentService appointmentService;
    
    @Autowired
    public AppointmentsRestController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}
    
    @RequestMapping(value = "/api/appointments/{start}/{end}", method = RequestMethod.GET)
    @ResponseBody
    public List<AppointmentDTO> getAppointmentsDTO(@PathVariable(name = "start", required = true) String strDateStart, 
    											   @PathVariable(name = "end", required = true) String strDateEnd) {
    	
    	//this is to validate incoming dates:
    	LocalDate dateStart = stringToDate(strDateStart);
    	LocalDate dateEnd = stringToDate(strDateEnd);
    	if(dateStart.isAfter(dateEnd)) {
    		throw new ExtendedRuntimeException(ErrorsEnum.WRONG_DATA_PASSED);
    	}
    	//
    	
		return appointmentService.getAllAppointmentsDTO(strDateStart, strDateEnd);
    }
    
    @RequestMapping(value = "/api/appointments", method = RequestMethod.POST)
    @ResponseBody
    public boolean addAppointment(@RequestBody AppointmentDTO appointmentDTO) throws IOException {
		return appointmentService.addAppointment(appointmentDTO);
    }
    
    @RequestMapping(value = "/api/appointments", method = RequestMethod.PUT)
    @ResponseBody
    public boolean updateServiceProvided(@RequestBody AppointmentDTO appointmentDTO) throws IOException {
    	return appointmentService.updateServiceProvided(appointmentDTO);
    }
    
    private LocalDate stringToDate(String stringDate) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
        	return LocalDate.parse(stringDate, formatter);
		} catch (Exception e) {
			throw new ExtendedRuntimeException(ErrorsEnum.WRONG_DATA_PASSED, e);
		}
	}
}