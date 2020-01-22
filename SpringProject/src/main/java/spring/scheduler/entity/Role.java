package spring.scheduler.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_ANONYMOUS,
	ROLE_USER,
	ROLE_MASTER,
	ROLE_ADMIN;
	
	public static final Role DEFAULT_ROLE = ROLE_USER; //for new registering users
	
	@Override
	public String getAuthority() {
		return name();
	}
}