package ua.training;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ua.training.entities.User;

public class Main {

	public static void main(String[] args) {
		
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);

        User user = context.getBean("adminBean", User.class);
        
		System.out.println("Hello annotations "+user.getName()+" ("+user.getUserRole()+")");
		
		context.close();
        
	}

}
