package spring.scheduler.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.scheduler.dto.UserDTO;
import spring.scheduler.entity.Role;
import spring.scheduler.entity.ServiceType;
import spring.scheduler.service.UserService;

@Controller
public class MainController {
	private UserService userService;
	
    @Value("${app.WORK_TIME_STARTS}")
    private int WORK_TIME_STARTS;
	
    @Value("${app.WORK_TIME_ENDS}")
    private int WORK_TIME_ENDS;
	
	@Autowired
    public MainController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
    public String root(Model model) {
		model.addAttribute("WORK_TIME_STARTS", WORK_TIME_STARTS);
		model.addAttribute("WORK_TIME_ENDS", WORK_TIME_ENDS);
		model.addAttribute("serviceTypes", ServiceType.values());
        
    	return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    @GetMapping("/admin")
    public String admin(Model model) {
    	List<UserDTO> allUsers = userService.getAllUsers(Sort.by(Direction.DESC, "id"))
    			.stream()
    			.map(u -> userService.userToDTO(u))
    			.collect(Collectors.toList());    	
    	
    	model.addAttribute("users", allUsers);
    	
    	model.addAttribute("roles", Role.values());
    	
    	List<String> serviceTypes = Arrays.asList(ServiceType.values())
    			.stream()
    			.map(s -> s.name())
    			.collect(Collectors.toList());
    	serviceTypes.add(0, ""); //prefer to leave business logic in Model, so add empty item here
    	model.addAttribute("serviceTypes", serviceTypes);
    	
        return "admin";
    }
}