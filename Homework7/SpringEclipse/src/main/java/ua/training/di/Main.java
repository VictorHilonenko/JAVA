package ua.training.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		  ClassPathXmlApplicationContext context = new
		  ClassPathXmlApplicationContext("applicationContextDI.xml");
		  
		  //Spring injected it: Music music = context.getBean("musicBean", Music.class);
		  
		  MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
		  
		  musicPlayer.playMusic();
		  
		  context.close();
		
	}

}
