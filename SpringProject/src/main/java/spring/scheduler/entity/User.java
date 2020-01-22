package spring.scheduler.entity;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table( name="users", 
		uniqueConstraints = @UniqueConstraint(columnNames = "email"), 
		indexes = {@Index(name = "IDX_ROLE", columnList = "role"), @Index(name = "IDX_SERVICETYPE", columnList = "serviceType")})
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
	private String firstName;
    
    @Column(nullable = false)
	private String lastName;
    
    @Column(nullable = false)
	private String email;
    
    @Column(nullable = false)
	private String telNumber;
    
    @Column(nullable = false)
	private String password;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
	private Role role;
    
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
	private ServiceType serviceType;
    
    //also this class now implements UserDetails interface, that was needed for autologin by quick link
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(role);
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		//that is out of business logic scope for this project 
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		//that is out of business logic scope for this project 
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//that is out of business logic scope for this project 
		return false;
	}

	@Override
	public boolean isEnabled() {
		//that is out of business logic scope for this project 
		return true;
	}
}