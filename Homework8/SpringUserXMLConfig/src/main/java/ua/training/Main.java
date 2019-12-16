package ua.training;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.training.entities.User;

public class Main {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		User user = context.getBean("adminBean", User.class);
		
		System.out.println("Hello XML config "+user.getName()+" ("+user.getUserRole()+")");
		
		context.close();
		
	}

}
