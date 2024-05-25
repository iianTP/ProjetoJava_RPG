package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import entities.Stats;

public class UI {
	
	private Font font; //= new Font("Arial", Font.PLAIN, 20);
	
	private BufferedImage battleUI;
	
	private Graphics2D brush;
	
	public UI() {
		
		setBattleUI();
		InputStream is = getClass().getResourceAsStream("/fonts/ARCADEPI.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 20F);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void pauseScreen() {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("PAUSE", 550, 48);
		
	}
	
	public void dialogueBox() {
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(48, 48, 48*13, 48*4, 10, 10);
		
	}
	
	public void dialogueText(String dialogue) {
		
		brush.setFont(font);
		brush.setColor(Color.white);
		brush.drawString(dialogue, 48+24, 48*2);
		
	}
	
	public void battleScreen(Stats playerStats, Stats enemieStats) {
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(0, 0, 720, 720, 10, 10);
		brush.drawImage(this.battleUI, 0, 0, 720, 720, null);
		
		brush.setFont(font);
		brush.setColor(Color.green);
		brush.drawString("HP: " + playerStats.getHealth() +"/"+ playerStats.getMaxHealth(), 28*16, 26*16);
		brush.drawString("HP: " + enemieStats.getHealth() +"/"+ enemieStats.getMaxHealth(), 3*16, 3*16);
		
		brush.setColor(Color.magenta);
		brush.drawString("Mana: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 28*16, 28*16);
		
	}
	
	public void draw(int x, int y) {
		
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

	public Graphics2D getBrush() {
		return brush;
	}

	public void setBrush(Graphics2D brush) {
		this.brush = brush;
	}

}
