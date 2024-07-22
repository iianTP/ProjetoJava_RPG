package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	
	private Clip clip;
	
	private void setClip(String music) {
		URL musicURL = getClass().getResource("/sounds/"+music+".wav");
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL);
			this.clip = AudioSystem.getClip();
			this.clip.open(ais);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void playMusic(String music) {
		
		this.setClip(music);
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stopMusic() {
		this.clip.stop();
	}
	
	public void resume() {
		this.clip.start();
	}

}
