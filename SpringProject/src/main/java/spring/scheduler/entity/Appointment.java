package spring.scheduler.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="appointments")
public class Appointment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
	private LocalDate appointmentDate;
    
    @Column(nullable = false)
	private Byte appointmentTime; //in this Project logic scope time will be only integer, like 8...20
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private ServiceType serviceType;
    
    @OneToOne
    @JoinColumn(nullable = false)
    private User customer;
    
    @OneToOne
    @JoinColumn(nullable = false)
    private User master;
    
    @Column(nullable = false, columnDefinition = "bit(1) DEFAULT 0")
	private Boolean serviceProvided;
}