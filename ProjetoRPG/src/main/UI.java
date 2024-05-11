package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
	
	Font font = new Font("Arial", Font.PLAIN, 30);
	
	public void pauseScreen(Graphics2D brush) {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("PAUSE", 550, 48);
		
	}
	
	public void draw(Graphics2D brush) {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("afijub", 48, 48);
		
	}

}
