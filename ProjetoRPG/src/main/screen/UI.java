package main.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import entities.Stats;
import main.KeyInput;

public class UI {
	
	private Font font; //= new Font("Arial", Font.PLAIN, 20);
	
	private BufferedImage battleUI;
	
	private Graphics2D brush;
	
	private KeyInput key;
	
	private int[][] battleButtonSelected = {{460, 533}, {571, 533}, 
											{460, 581}, {571, 581}, 
											{460, 629}, {571, 629}};
	
	public UI(KeyInput key) {
		
		setBattleUI();
		InputStream is = getClass().getResourceAsStream("/fonts/ARCADEPI.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(Font.PLAIN, 20F);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		this.key = key;
		
	}
	
	// PAUSE
	public void pauseScreen() {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("PAUSE", 550, 48);
		
	}
	
	// CAIXA DE DIALOGO
	public void dialogueBox() {
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(48, 48, 48*13, 48*4, 10, 10);
		
	}
	
	// DIALOGO
	public void dialogueText(String dialogue) {
		
		brush.setFont(font);
		brush.setColor(Color.white);
		brush.drawString(dialogue, 48+24, 48*2);
		
	}
	
	// TELA DE COMBATE
	public void battleScreen(Stats playerStats, Stats enemieStats) {
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(0, 0, 720, 720, 10, 10);
		brush.drawImage(this.battleUI, 0, 0, 720, 720, null);
		
		brush.setFont(font.deriveFont(Font.PLAIN, 15F));
		
		brush.setColor(Color.red);
		brush.fillRect(64, 233, 96, 5);
		brush.fillRect(64, 275, 96, 5);
		brush.fillRect(64, 317, 96, 5);
		brush.fillRect(64, 359, 96, 5);
		brush.fillRect(568, 41, 96, 5);
		
		brush.setColor(Color.green);
		brush.drawString("HP: ", 24, 240);
		brush.fillRect(64, 233, 96*playerStats.getHealth()/playerStats.getMaxHealth(), 5);
		brush.drawString("HP: ", 24, 282);
		brush.fillRect(64, 275, 96*playerStats.getHealth()/playerStats.getMaxHealth(), 5);
		brush.drawString("HP: ", 24, 324);
		brush.fillRect(64, 317, 96*playerStats.getHealth()/playerStats.getMaxHealth(), 5);
		brush.drawString("HP: ", 24, 366);
		brush.fillRect(64, 359, 96*playerStats.getHealth()/playerStats.getMaxHealth(), 5);
		
		brush.drawString("HP: ", 528, 48);
		brush.fillRect(568, 41, 96*enemieStats.getHealth()/enemieStats.getMaxHealth(), 5);
		
		brush.setColor(Color.magenta);
		brush.drawString("Mana: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 261);
		brush.drawString("Mana: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 303);
		brush.drawString("Mana: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 345);
		brush.drawString("Mana: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 387);
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		brush.drawString("ATAQUE", 475, 533); // cmdNum = 0
		brush.drawString("DEFESA", 586, 533); // cmdNum = 1
		brush.drawString("MAGIA", 475, 581);  // cmdNum = 2
		brush.drawString("BOLSA", 586, 581);  // cmdNum = 3
		brush.drawString("ESPEC.", 475, 629); // cmdNum = 4
		brush.drawString("FUGIR", 586, 629);  // cmdNum = 5

		brush.drawString(">", this.battleButtonSelected[this.key.getCmdNum()][0], this.battleButtonSelected[this.key.getCmdNum()][1]);
		
	}
	
	public void battleText(String message) {
		
		// 16 176
		brush.setFont(font.deriveFont(Font.PLAIN, 14F));
		brush.setColor(Color.white);
		brush.drawString("- "+message, 16*3, 176*3);
		
	}
	
	// TESTE(COORDENADAS)
	public void draw(int x, int y) {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("wX: " + x + " wY: " + y, 48, 48);
		
	}

	public void setBattleUI() {
		try {
			this.battleUI = ImageIO.read(getClass().getResourceAsStream("/battle/battleUI.png"));
		} catch (IOException e) {
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