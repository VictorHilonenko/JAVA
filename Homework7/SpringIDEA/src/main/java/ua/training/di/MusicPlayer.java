package ua.training.di;

public class MusicPlayer {
	private Music music;
	
	public MusicPlayer(Music music) {
		this.music = music;
	}
	
	public void playMusic() {
		//music = new ClassicalMusic(); //IoC moves it to Constructor 
		
		System.out.println("playing "+music.getSong());
	}
}
