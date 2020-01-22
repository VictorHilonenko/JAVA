package spring.scheduler.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.scheduler.dto.UserDTO;
import spring.scheduler.entity.User;
import spring.scheduler.exceptions.ErrorsEnum;
import spring.scheduler.exceptions.ExtendedRuntimeException;
import spring.scheduler.service.UserService;

@RestController()
public class UsersRestController {
	private UserService userService;
    
    @Autowired
	public UsersRestController(UserService userService) {
		this.userService = userService;
	}
    
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> getAllUsers() {
    	return userService.getAllUsers()
			.stream()
			.map(u -> userService.userToDTO(u))
			.collect(Collectors.toList());
    }
    
    @RequestMapping(value = "/api/users/{email}", method = RequestMethod.GET)
    @ResponseBody
    public UserDTO getUser(@PathVariable("email") String email) {
		return userService.userToDTO(userService.findByEmail(email)
												.orElseThrow(() -> new ExtendedRuntimeException(ErrorsEnum.USER_NOT_FOUND)));		
    }
    
    @RequestMapping(value = "/api/users", method = RequestMethod.PUT)
    @ResponseBody
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
		User updatedUser = userService.updateUser(userDTO);
		
		return userService.userToDTO(updatedUser);
    }
    
    //for learning purposes, actually we don't use it
    @RequestMapping(value = "/api/users/{email}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable("email") String email) {
		return userService.deleteUser(email);
    }
}