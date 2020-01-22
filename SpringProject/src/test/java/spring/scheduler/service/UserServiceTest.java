package spring.scheduler.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import spring.scheduler.dto.UserDTO;
import spring.scheduler.entity.Role;
import spring.scheduler.entity.User;
import spring.scheduler.exceptions.ExtendedRuntimeException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Before
	public void createTestObjects() {
		
	}
	
	@After
	public void deleteTestObjects() {
		
	}
	
	@Test
	public void testFindById() {
		Optional<User> existingUser = userService.findById(1L);
		System.out.println("testFindById "+existingUser);
		assertTrue(existingUser.isPresent());
	}

	@Test
	public void testFindByEmail() {
		Optional<User> existingUser = userService.findByEmail("viking33@ukr.net");
		System.out.println("testFindByEmail "+existingUser);
		assertTrue(existingUser.isPresent());
	}

	@Test
	public void testAddUserPlusFoundByEmailPlusDelete() {
		String email = "email for test";
		
		UserDTO userDTO = new UserDTO(); 
		userDTO.setEmail(email);
		userDTO.setFirstName("fisrtName");
		userDTO.setLastName("lastName");
		userDTO.setTelNumber("telNumber");
		userDTO.setPassword("password");
		
		User addedUser = userService.addUser(userDTO);
		
		Optional<User> foundUser = userService.findByEmail(email);
		if(foundUser.isPresent()) {
			assertEquals(foundUser.get().getEmail(), addedUser.getEmail());
		} else {
			fail();
		}
		
		userService.deleteUser(email);
		
		foundUser = userService.findByEmail(email);
		
		assertFalse(foundUser.isPresent());
	}

	@Test(expected = ExtendedRuntimeException.class)
	public void testUpdateUserThrowsException() {
		UserDTO userDTO = new UserDTO(); 
		userDTO.setEmail("impossible email");
		
		userService.updateUser(userDTO);
	}

	@Test
	public void testUpdateUser() {
		UserDTO userDTO = new UserDTO(); 
		userDTO.setEmail("viking33@ukr.net");
		userDTO.setFirstName("fisrtName");
		userDTO.setLastName("lastName");
		userDTO.setTelNumber("telNumber");
		userDTO.setRole("ROLE_ADMIN");
		
		User updatedUser = userService.updateUser(userDTO);
		assertEquals(updatedUser.getRole(), Role.ROLE_ADMIN);
	}

	@Test
	public void testGetAllUsers() {
		
		userService.getAllUsers()
		.stream()
		.forEach(System.out::println);
		
		assertTrue(true);
	}
}
