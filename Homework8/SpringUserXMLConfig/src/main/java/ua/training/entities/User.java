package ua.training.entities;

import org.springframework.stereotype.Component;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Component
public class User {
	private String login;
	private String passwordHash;
	private String name;
	private UserRoles userRole;
	
	public User() {
		this.login = "";
		this.passwordHash = "";
		this.name = "";
		this.userRole = UserRoles.USER;
	}
}
