package spring.scheduler.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.scheduler.dto.UserDTO;
import spring.scheduler.entity.EmailMessage;
import spring.scheduler.entity.Role;
import spring.scheduler.entity.ServiceType;
import spring.scheduler.entity.User;
import spring.scheduler.exceptions.ErrorsEnum;
import spring.scheduler.exceptions.ExtendedRuntimeException;
import spring.scheduler.repository.UserRepository;
import spring.scheduler.utils.UserAuthentication;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private EmailMessageService emailMessageService;
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, EmailMessageService emailMessageService, BCryptPasswordEncoder passwordEncoder) { 
		this.userRepository = userRepository;
		this.emailMessageService = emailMessageService;
		this.passwordEncoder = passwordEncoder;
	}

	public Optional<User> findById (Long id) {
        try {
	        return userRepository.findById(id);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }

    public Optional<User> findByEmail (String email) {
        try {
	        return userRepository.findByEmail(email);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
    public List<User> getAllUsers() {
        try {
	        return userRepository.findAll();
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
    public List<User> getAllUsers(Sort sort) {
        try {
	    	return userRepository.findAll(sort);
		} catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
    public User addUser(UserDTO userDTO) {
    	User newUser = userFromDTO(userDTO);
    	newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
    	
        try {
	        return userRepository.save(newUser);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
    }
    
    public User updateUser(UserDTO userDTO) {
    	User user = findByEmail(userDTO.getEmail())
    				.orElse(findById(userDTO.getId())
    				.orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.USER_NOT_FOUND)));
        
    	//we don't change these fields in this project, so now it's commented:
		//	user.setFirstName(userDTO.getFirstName());
		//	user.setLastName(userDTO.getLastName());
		//	user.setTelNumber(userDTO.getTelNumber());
		//	user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    	
        try {
	    	user.setRole(Role.valueOf(userDTO.getRole()));
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.WRONG_DATA_PASSED, e);
		}
    	
    	user.setServiceType(ServiceType.lookup(userDTO.getServiceType()));
    	
        try {
    		userRepository.save(user);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
        
    	return user;
    }
    
    //for learning purposes, actually we don't use it
	public boolean deleteUser(String email) {
    	User user = findByEmail(email)
					.orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.USER_NOT_FOUND));

        try {
	        userRepository.delete(user);
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
        
        return true;
    }
    
    public void deleteUser(UserDTO userDTO) {
    	deleteUser(userDTO.getEmail());
    }
 
    public UserDTO userToDTO(User user) {
    	return UserDTO.builder()
				.id(user.getId())
				.email(user.getEmail())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.telNumber(user.getTelNumber())
				.role(user.getRole().name())
				.serviceType(Optional.ofNullable(user.getServiceType()).map(ServiceType::name).orElse(""))
				.build();
    }
    
    public User userFromDTO(UserDTO userDTO) {
    	return User.builder()
    			.id(userDTO.getId())
				.email(userDTO.getEmail())
				.firstName(userDTO.getFirstName())
				.lastName(userDTO.getLastName())
				.telNumber(userDTO.getTelNumber())
				.password(userDTO.getPassword())
				.role(Role.DEFAULT_ROLE)
				.serviceType(ServiceType.lookup(userDTO.getServiceType()))
				.build();
    }
    
    //this method is used for admin users to show them extended info:
    public String getFullNamePlusTelNumber(User user) {
    	return user.getFirstName() + " " + user.getLastName() + " \n(" + user.getTelNumber() + ")";
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	User user = findByEmail(email)
					.orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.USER_NOT_FOUND));
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Arrays.asList(user.getRole()));
    }

    public String getCurrentUserName() {
    	return SecurityContextHolder
    			.getContext()
    			.getAuthentication()
    			.getName();
    }
    
    private String getCurrentAuthority() {
    	return SecurityContextHolder
    			.getContext()
    			.getAuthentication()
				.getAuthorities()
				.stream()
				.findFirst()
				.get()
				.getAuthority();
    }
    
    public Role getCurrentRole() {
    	return Role.valueOf(getCurrentAuthority());
    }
    
	public Optional<User> getCurrentUser() {
        try {
			return userRepository.findByEmail(getCurrentUserName());
        } catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
	}
	
    private void setAuthentication(String email) {
    	User user = findByEmail(email)
    				.orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.USER_NOT_FOUND));
    	
        SecurityContextHolder
        	.getContext()
        	.setAuthentication(new UserAuthentication(user));
    }
    
	public void setAuthenticationByQuickAccessCode(String quickAccessCode) {
		EmailMessage emailMessage = emailMessageService.findEmailMessageByQuickAccessCode(quickAccessCode)
													   .orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.PAGE_NOT_FOUND_ERROR));

		emailMessage.setDateLinkOpened(LocalDate.now());
		emailMessage.setQuickAccessCode("");
		
		try {
			emailMessageService.save(emailMessage);
		} catch (Exception e) {
	    	throw new ExtendedRuntimeException(ErrorsEnum.REPOSITORY_ISSUE, e);
		}
		
	 	try {
		    setAuthentication(emailMessage.getEmail());
	 	} catch (Exception e) {
	 		//
	 	}
	}
}