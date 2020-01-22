package spring.scheduler.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import spring.scheduler.entity.EmailMessage;
import spring.scheduler.exceptions.ErrorsEnum;
import spring.scheduler.exceptions.ExtendedRuntimeException;
import spring.scheduler.repository.EmailMessageRepository;

@Service
public class EmailMessageService {
    private EmailMessageRepository emailMessageRepository;
    private JavaMailSender mailSender;
    
	@Value("${spring.mail.from}")
    private String emailFrom;
    
    @Autowired
    public EmailMessageService(EmailMessageRepository emailMessageRepository, JavaMailSender mailSender) {
		this.emailMessageRepository = emailMessageRepository;
		this.mailSender = mailSender;
	}

    public Optional<EmailMessage> findEmailMessageByEmail(String email) {
        try {
        	return emailMessageRepository.findByEmail(email);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
    public Optional<EmailMessage> findEmailMessageByQuickAccessCode(String quickAccessCode) {
        try {
        	return emailMessageRepository.findEmailMessageByQuickAccessCode(quickAccessCode);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
    public void addEmailToQueue(String email, String subject, String textMessage, String quickAccessCode) {
    	EmailMessage emailMessage = EmailMessage.builder()
    			.email(email)
    			.subject(subject)
    			.textMessage(textMessage)
    			.quickAccessCode(quickAccessCode)
    			.dateCreated(LocalDate.now())
    			.build();
    	
        try {
        	emailMessageRepository.save(emailMessage);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
	public void sendAnEmail(EmailMessage emailMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(emailFrom);
        mailMessage.setTo(emailMessage.getEmail());
        mailMessage.setSubject(emailMessage.getSubject());
        mailMessage.setText(emailMessage.getTextMessage());

        try {
	        mailSender.send(mailMessage);
        } catch (Exception e) {
        	//TODO log this exception
	    	//throw new ExtendedRuntimeException(ErrorsEnum.MAIL_ISSUE, e);
        }
        
        emailMessage.setDateSent(LocalDate.now());
        try {
	        emailMessageRepository.save(emailMessage);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
	public void sendUnsentEmailsFromQueue() {
		Runnable runnable = () -> {
			List<EmailMessage> listUnsent;
			
			try {
				listUnsent = emailMessageRepository.findUnsent();
	        } catch (Exception e) {
		    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
			}
			
			listUnsent.stream()
			.forEach(e -> sendAnEmail(e));
        };
        
        runnable.run();
	}
    
	public void deleteOld() {
		Runnable runnable = () -> {
			LocalDate oldDate = LocalDate.now();
			
			//TODO 30 to props
			oldDate = oldDate.minusDays(30);
			
	        try {
		        emailMessageRepository.deleteOld(oldDate);
	        } catch (Exception e) {
		    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
			}
        };
        
        runnable.run();
    }

	public void save(EmailMessage emailMessage) {
        try {
			emailMessageRepository.save(emailMessage);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}
}