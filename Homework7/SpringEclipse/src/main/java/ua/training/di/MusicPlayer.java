package ua.training.di;

public class MusicPlayer {
	private Music music;
	private String name;
	private int volume;
	
	public MusicPlayer() {
	}
	
	public MusicPlayer(Music music) {
		this.music = music;
	}
	
	public void playMusic() {
		//music = new ClassicalMusic(); //IoC moves it to Constructor 
		
		System.out.println("playing "+music.getSong());
	}
	
	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
