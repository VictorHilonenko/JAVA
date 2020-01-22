package spring.scheduler.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import spring.scheduler.dto.FeedbackDTO;
import spring.scheduler.entity.Feedback;
import spring.scheduler.entity.User;
import spring.scheduler.exceptions.ErrorsEnum;
import spring.scheduler.exceptions.ExtendedRuntimeException;
import spring.scheduler.repository.FeedbackRepository;

@Service
public class FeedbackService {
    private FeedbackRepository feedbackRepository;
    private UserService userService;
    private AppointmentService appointmentService;
    
    @Autowired
	public FeedbackService(FeedbackRepository feedbackRepository,
						   UserService userService,
						   AppointmentService appointmentService) {

        //NOTE: as we considered to NORMALIZE feedbacks table, there will be less dependencies a bit later
        
    	this.feedbackRepository = feedbackRepository;
		this.userService = userService;
		this.appointmentService = appointmentService;
	}
    
	public void createNewFeedbacksOnProvidedServicesForCustomer(User user) {
        try {
			feedbackRepository.createNewFeedbacksOnProvidedServicesForCustomer(user.getId().toString());
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}

	public List<Feedback> getFeedbacksToLeave(User user) {
        try {
			return feedbackRepository.findFeedbacksToLeave(user.getId());
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}

	public Page<Feedback> getCustomersFeedbacks(User user, Pageable pageable) {
        try {
			return feedbackRepository.findAllLeftByCustomerId(user.getId(), pageable);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}
	
	public Page<Feedback> getMastersFeedbacks(User user, Pageable pageable) {
        try {
			return feedbackRepository.findAllLeftForMasterId(user.getId(), pageable);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}

	public Page<Feedback> getAllFeedbacks(User user, Pageable pageable) {
        try {
			return feedbackRepository.findAllLeft(pageable);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}

	public void checkFeedbackDTO(FeedbackDTO feedbackDTO, BindingResult result) {
		Byte rating = Optional.ofNullable(feedbackDTO.getRating()).orElse((byte) 0);
		String strTextMessage = Optional.ofNullable(feedbackDTO.getTextMessage()).orElse("");
		
		if(rating == 0 || "".equals(strTextMessage)) {
			result.rejectValue("textMessage", null, "you need to fill rating and text");
		}
		
		if(rating < 0 || rating > 10) {
			result.rejectValue("rating", null, "wrong rating");
		}
	}
	
	public Feedback findAndCheckFeedback(Long feedbackId) {
    	User user = userService.getCurrentUser()
    						   .orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.ACCESS_DENIED));
 	
	 	Feedback feedback = feedbackRepository.findById(feedbackId)
	 										  .orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.WRONG_DATA_PASSED));
	 	
		if(!feedback.getAppointment().getCustomer().equals(user)) {
			throw new ExtendedRuntimeException(ErrorsEnum.ACCESS_DENIED);
		}
		
		Byte ratingExisting = Optional.ofNullable(feedback.getRating()).orElse((byte) 0);
		String strTextMessageExisting = Optional.ofNullable(feedback.getTextMessage()).orElse("");
		
		if(ratingExisting > 0 || !"".equals(strTextMessageExisting)) {
			//in this business logic we don't allow "rewriting" in any case, so
			throw new ExtendedRuntimeException(ErrorsEnum.NOT_ACCEPTABLE_DATA_PASSED);
		}
		
		return feedback;
	}

	public void updateFeedback(Feedback feedback, FeedbackDTO feedbackDTO) {
		feedback.setRating(feedbackDTO.getRating());
		feedback.setTextMessage(feedbackDTO.getTextMessage());
        try {
			feedbackRepository.save(feedback);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}

	public void processQuickLink(String quickAccessCode, Long appointmentId) {
		appointmentService.findById(appointmentId)
		  .orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.PAGE_NOT_FOUND_ERROR));
		
	 	try {
		    userService.setAuthenticationByQuickAccessCode(quickAccessCode);
	 	} catch (Exception e) {
	 		//here we do nothing: 
	 		//it's to forward a user to feedbacks or login page
	 	}
	}
}