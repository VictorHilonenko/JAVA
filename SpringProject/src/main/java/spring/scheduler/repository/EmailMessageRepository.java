package spring.scheduler.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.scheduler.entity.EmailMessage;

@Repository
public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {
	
	Optional<EmailMessage> findByEmail(String email);
	
	Optional<EmailMessage> findEmailMessageByQuickAccessCode(String quickAccessCode);
	
	@Query(value = "SELECT \n" +
    		"    `email_messages`.`id`,\n" + 
    		"    `email_messages`.`email`,\n" + 
    		"    `email_messages`.`subject`,\n" + 
    		"    `email_messages`.`text_message`,\n" + 
    		"    `email_messages`.`date_created`,\n" + 
    		"    `email_messages`.`date_sent`,\n" + 
    		"    `email_messages`.`date_link_opened`,\n" + 
    		"    `email_messages`.`quick_access_code`" + 
    		"FROM \n" + 
    		"	`email_messages`\n" + 
    		"WHERE\n" + 
    		"	`date_sent` is null",
    		nativeQuery = true)
	List<EmailMessage> findUnsent();
    
    @Query(value = "DELETE FROM \n" + 
    		"	`email_messages`\n" + 
    		"WHERE\n" + 
    		"	date_created < :old_date_created",
    		nativeQuery = true)
    void deleteOld(@Param("old_date_created") LocalDate oldDateCreated);
}
