package ua.training.first_example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext1.xml");
		TestBean testBean = context.getBean("testBean", TestBean.class);
		
		System.out.println(testBean.getName());
		
		context.close();
    }
}
