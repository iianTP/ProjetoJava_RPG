package main.screen;

import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TextAnimation {
	
	private int timer;
	private int count;
	
	private int letterPosition;
	private int skipLine;
	private int arrowPosition;
	
	private boolean textEnded = false;
	
	private Clip clip;
	private URL soundURL = getClass().getResource("/sounds/textSound.wav");
	
	public TextAnimation() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(this.soundURL);
			this.clip = AudioSystem.getClip();
			this.clip.open(ais);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void displayText(String text, int speed, int width, int letterSpacement, int startX, int startY, boolean sound, Graphics2D brush) {
		
		String[] letters = text.split("");
		
		if (this.textEnded) {
			for (int i = 0; i < letters.length; i++) {
				this.letterPosition = i - (i/width)*width;
				this.skipLine = i/width;
				brush.drawString(letters[i], startX+letterSpacement*letterPosition, startY+24*skipLine);
			}
		} else {
			
			if (this.count >= letters.length) {
				timer = 0;
				count = 0;
				this.textEnded = true;
			}
			for (int i = 0; i < this.count; i++) {
				this.letterPosition = i - (i /width)*width;
				this.skipLine = i/width;
				brush.drawString(letters[i], startX+letterSpacement*letterPosition, startY+24*skipLine);
			}
			this.timer++;
			if (this.timer > speed) {
				if (sound) {
					this.clip.setMicrosecondPosition(0);
					this.clip.start();
				}
				this.timer=0;
				this.count++;
			}
			
		}
		
	}
	
	public void checkStateChange(boolean changedState, int cmdNum) {
		
		if (changedState || this.arrowPosition != cmdNum) {
			timer = 0;
			count = 0;
			this.arrowPosition = cmdNum;
			this.textEnded = false;
		}
		
	}
	
	public void resetTextAnimation() {
		timer = 0;
		count = 0;
		this.arrowPosition = -1;
		this.textEnded = false;
	}
	
	public int getCount() {
		return this.count;
	}
	
}
