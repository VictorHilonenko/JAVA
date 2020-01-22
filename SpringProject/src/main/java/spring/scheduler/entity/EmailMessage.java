package spring.scheduler.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table( name="email_messages",
		indexes = {@Index(name = "IDX_DATESENT", columnList = "dateSent"), @Index(name = "IDX_QUICKACCESSCODE", columnList = "quickAccessCode")})
public class EmailMessage {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
	private String email;
    
    @Column(nullable = false)
	private String subject;
    
    @Column(columnDefinition="TEXT")
	private String textMessage;
    
    @Column(nullable = false)
	private LocalDate dateCreated;
    
    @Column(nullable = true)
	private LocalDate dateSent;
    
    @Column(nullable = true)
	private LocalDate dateLinkOpened;
    
    @Column(nullable = true)
	private String quickAccessCode;
}