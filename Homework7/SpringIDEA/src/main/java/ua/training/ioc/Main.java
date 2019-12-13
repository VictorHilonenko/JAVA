package ua.training.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		  ClassPathXmlApplicationContext context = new
		  ClassPathXmlApplicationContext("applicationContextIOC.xml");
		  Music music = context.getBean("musicBean", Music.class);
		  
		  MusicPlayer musicPlayer = new MusicPlayer(music);
		  
		  musicPlayer.playMusic();
		  
		  context.close();
		
	}

}
