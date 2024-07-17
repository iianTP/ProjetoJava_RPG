package main.screen;

import java.awt.Graphics2D;

public class TextAnimation {
	
	private int timer;
	private int count;
	
	private int letterPosition;
	private int skipLine;
	private int arrowPosition;
	
	private boolean textEnded = false;
	
	public void displayText(String text, int width, int letterSpacement, int startX, int startY, Graphics2D brush) {
		
		String[] letters = text.split("");
		
		if (this.textEnded) {
			for (int i = 0; i < letters.length; i++) {
				this.letterPosition = i - (i /width)*width;
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
			if (this.timer > 2) {
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
