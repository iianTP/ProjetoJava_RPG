package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	
	private Font font = new Font("Arial", Font.PLAIN, 20);
	
	private BufferedImage battleUI;
	
	public UI() {
		setBattleUI();
	}
	
	public void pauseScreen(Graphics2D brush) {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("PAUSE", 550, 48);
		
	}
	
	public void dialogueBox(Graphics2D brush) {
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(48, 48, 48*13, 48*4, 10, 10);
		
	}
	
	public void dialogueText(Graphics2D brush, String dialogue) {
		
		brush.setFont(font);
		brush.setColor(Color.white);
		brush.drawString(dialogue, 48+24, 48*2);
		
	}
	
	public void battleScreen(Graphics2D brush) throws IOException {
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(0, 0, 720, 720, 10, 10);
		brush.drawImage(this.battleUI, 0, 0, 720, 720, null);
		
	}
	
	public void draw(Graphics2D brush, int x, int y) {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("wX: " + x + " wY: " + y, 48, 48);
		
	}

	public void setBattleUI() {
		try {
			this.battleUI = ImageIO.read(getClass().getResourceAsStream("/battle/battleUI.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
