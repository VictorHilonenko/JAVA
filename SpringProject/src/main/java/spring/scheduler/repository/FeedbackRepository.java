package spring.scheduler.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.scheduler.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
    @Transactional
    @Modifying
    @Query(value = 
		"INSERT INTO `feedbacks`\n" + 
		"	(`appointment_id`,\n" + 
		"    `appointment_date`,\n" + 
		"    `appointment_time`,\n" + 
		"    `service_type`,\n" + 
		"    `customer_id`,\n" + 
		"    `master_id`,\n" + 
		"    `rating`,\n" + 
		"    `text_message`)\n" + 
		"SELECT\n" + 
		"	`appointments`.`id`,\n" + 
		"	`appointments`.`appointment_date`,\n" + 
		"	`appointments`.`appointment_time`,\n" + 
		"	`appointments`.`service_type`,\n" + 
		"	`appointments`.`customer_id`,\n" + 
		"	`appointments`.`master_id`,\n" + 
		"	0,\n" + 
		"	''\n" + 
		"FROM\n" + 
		"	`appointments`\n" + 
		"LEFT JOIN\n" + 
		"	`feedbacks` ON `feedbacks`.`appointment_id` = `appointments`.`id`\n" + 
		"WHERE\n" + 
		"	`appointments`.`customer_id` = :customer_id\n" + 
		"	AND `appointments`.`service_provided` = 1\n" + 
		"	AND `feedbacks`.`id` IS NULL",
		nativeQuery = true)
    Integer createNewFeedbacksOnProvidedServicesForCustomer(@Param("customer_id") String customer_id);
    
	@Query(value =
		"SELECT\n" + 
		"	`id`,\n" + 
		"	`appointment_id`,\n" + 
		"	`appointment_date`,\n" + 
		"	`appointment_time`,\n" + 
		"	`service_type`,\n" + 
		"	`customer_id`,\n" + 
		"	`master_id`,\n" + 
		"	`rating`,\n" + 
		"	`text_message`\n" + 
		"FROM\n" + 
		"	`feedbacks`\n" + 
		"WHERE\n" + 
		"	`customer_id` = :customer_id\n" + 
		"	AND `rating` = 0\n" + 
		"	AND `text_message` = ''",
		nativeQuery = true)
	List<Feedback> findFeedbacksToLeave(@Param("customer_id") Long customer_id);
    
	@Query(value =
		"SELECT\n" + 
		"	`id`,\n" + 
		"	`appointment_id`,\n" + 
		"	`appointment_date`,\n" + 
		"	`appointment_time`,\n" + 
		"	`service_type`,\n" + 
		"	`customer_id`,\n" + 
		"	`master_id`,\n" + 
		"	`rating`,\n" + 
		"	`text_message`\n" + 
		"FROM\n" + 
		"	`feedbacks`\n" + 
		"WHERE\n" + 
		"	`customer_id` = :customer_id\n" + 
		"	AND (`rating` != 0\n" + 
		"	OR `text_message` != '')",
		countQuery =
		"SELECT \n" + 
		"	COUNT(*) \n" + 
		"FROM\n" + 
		"	`feedbacks`\n" + 
		"WHERE\n" + 
		"	`customer_id` = :customer_id\n" + 
		"	AND (`rating` != 0\n" + 
		"	OR `text_message` != '')",
		nativeQuery = true)
	Page<Feedback> findAllLeftByCustomerId(@Param("customer_id") Long customer_id, Pageable pageable);

	@Query(value =
		"SELECT\n" + 
		"	`id`,\n" + 
		"	`appointment_id`,\n" + 
		"	`appointment_date`,\n" + 
		"	`appointment_time`,\n" + 
		"	`service_type`,\n" + 
		"	`customer_id`,\n" + 
		"	`master_id`,\n" + 
		"	`rating`,\n" + 
		"	`text_message`\n" + 
		"FROM\n" + 
		"	`feedbacks`\n" + 
		"WHERE\n" + 
		"	`master_id` = :master_id\n" + 
		"	AND (`rating` != 0\n" + 
		"	OR `text_message` != '')",
		countQuery =
		"SELECT \n" + 
		"	COUNT(*) \n" + 
		"FROM\n" + 
		"	`feedbacks`\n" + 
		"WHERE\n" + 
		"	`master_id` = :master_id\n" + 
		"	AND (`rating` != 0\n" + 
		"	OR `text_message` != '')",
		nativeQuery = true)
	Page<Feedback> findAllLeftForMasterId(@Param("master_id") Long master_id, Pageable pageable);
	
	@Query(value =
		"SELECT\n" + 
		"	`feedbacks`.`id`,\n" + 
		"	`feedbacks`.`appointment_id`,\n" + 
		"	`appointments`.`appointment_date`,\n" + 
		"	`appointments`.`appointment_time`,\n" + 
		"	`appointments`.`service_type`,\n" + 
		"	`appointments`.`customer_id`,\n" + 
		"	`appointments`.`master_id`,\n" + 
		"	`feedbacks`.`rating`,\n" + 
		"	`feedbacks`.`text_message`\n" + 
		"FROM\n" + 
		"	`feedbacks`\n" + 
		"LEFT JOIN\n" + 
		"	`appointments` ON `feedbacks`.`appointment_id` = `appointments`.`id`\n" + 
		"WHERE\n" + 
		"	`feedbacks`.`rating` != 0\n" + 
		"	OR `feedbacks`.`text_message` != ''",
		countQuery =
		"SELECT \n" + 
		"	COUNT(*) \n" + 
		"FROM\n" + 
		"	`feedbacks`\n" + 
		"WHERE\n" + 
		"	`feedbacks`.`rating` != 0\n" + 
		"	OR `feedbacks`.`text_message` != ''",
		nativeQuery = true)
	Page<Feedback> findAllLeft(Pageable pageable);
}