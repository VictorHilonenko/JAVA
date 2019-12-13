package ua.training.di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		  ClassPathXmlApplicationContext context = new
		  ClassPathXmlApplicationContext("applicationContextDI.xml");
		  
		  //Spring injected it: Music music = context.getBean("musicBean", Music.class);
		  
		  MusicPlayer musicPlayer1 = context.getBean("musicPlayer1", MusicPlayer.class);
		  
		  musicPlayer1.playMusic();
		  
		  MusicPlayer musicPlayer2 = context.getBean("musicPlayer2", MusicPlayer.class);
		  
		  musicPlayer2.playMusic();
		  System.out.println(musicPlayer2.getName() + " " + musicPlayer2.getVolume());
		  
		  context.close();
		
	}

}
