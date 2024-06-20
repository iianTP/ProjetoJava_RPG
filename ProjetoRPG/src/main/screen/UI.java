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
import entities.enemies.Enemie;
import entities.player.Player;
import habilities.Spells;
import main.KeyInput;

public class UI {
	
	private Font font; //= new Font("Arial", Font.PLAIN, 20);
	
	private BufferedImage battleUI;
	
	private Graphics2D brush;
	
	private final KeyInput key;
	
	private int battleButtonInitX = 475;
	private int battleButtonInitY = 550;
	
	private String playerMenuState = "main";
	private int[][] pMenuMainButtons = {{48+20, 48*3},{48+20, 48*4}};
	
	private int[][] battleButtons = {{battleButtonInitX-15, battleButtonInitY}, 
									 {battleButtonInitX-15+111, battleButtonInitY},
									 {battleButtonInitX-15, battleButtonInitY+48}, 
									 {battleButtonInitX-15+111, battleButtonInitY+48}, 
									 {battleButtonInitX-15, battleButtonInitY+48+48}, 
									 {battleButtonInitX-15+111, battleButtonInitY+48+48}};
	
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
	public void battleScreen(Player player, Enemie enemie, String battleState) {
		
		Stats playerStats = player.getStats();
		Stats enemieStats = enemie.getStats();
		
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
		brush.drawString("MP: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 261);
		brush.drawString("MP: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 303);
		brush.drawString("MP: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 345);
		brush.drawString("MP: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 387);
		
		battleOptionsBox(player, battleState);
		
	}
	
	public void battleOptionsBox(Player player, String battleState) {
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		
		if (battleState.equals("choose-move")) {
			
			brush.drawString("ATAQUE", battleButtonInitX, battleButtonInitY); // cmdNum = 0
			brush.drawString("DEFESA", battleButtonInitX+111, battleButtonInitY); // cmdNum = 1
			brush.drawString("MAGIA", battleButtonInitX, battleButtonInitY+48);  // cmdNum = 2
			brush.drawString("BOLSA", battleButtonInitX+111, battleButtonInitY+48);  // cmdNum = 3
			brush.drawString("ESPEC.", battleButtonInitX, battleButtonInitY+48+48); // cmdNum = 4
			brush.drawString("FUGIR", battleButtonInitX+111, battleButtonInitY+48+48);  // cmdNum = 5

		} else if (battleState.equals("choose-spell")) {
			
			Spells spells = player.getSpells();
			
			brush.setFont(font.deriveFont(Font.PLAIN, 18F));
			brush.drawString(spells.getSpell1(), battleButtonInitX, battleButtonInitY); // cmdNum = 0
			brush.drawString(spells.getSpell2(), battleButtonInitX+111, battleButtonInitY); // cmdNum = 1
			brush.drawString(spells.getSpell3(), battleButtonInitX, battleButtonInitY+48);  // cmdNum = 2
			brush.drawString(spells.getSpell4(), battleButtonInitX+111, battleButtonInitY+48);  // cmdNum = 3
			brush.drawString(spells.getSpell5(), battleButtonInitX, battleButtonInitY+48+48); // cmdNum = 4
			brush.setFont(font.deriveFont(Font.PLAIN, 18F));
			brush.drawString("VOLTAR", battleButtonInitX+111, battleButtonInitY+48+48);  // cmdNum = 5
			
		} /*else if (battleState.equals("choose-item")) {
		
			// Item[] items = player.getItems();
			
			brush.drawString("-", battleButtonInitX, battleButtonInitY); // cmdNum = 0
			brush.drawString("-", battleButtonInitX+111, battleButtonInitY); // cmdNum = 1
			brush.drawString("-", battleButtonInitX, battleButtonInitY+48);  // cmdNum = 2
			brush.drawString("-", battleButtonInitX+111, battleButtonInitY+48);  // cmdNum = 3
			brush.drawString("-", battleButtonInitX, battleButtonInitY+48+48); // cmdNum = 4
			brush.drawString("-->", battleButtonInitX+111, battleButtonInitY+48+48);  // cmdNum = 5
			
		}*/
		
		if (!battleState.equals("enemie-turn") && !battleState.equals("enemie-text")) {
			brush.drawString(">", this.battleButtons[this.key.getCmdNum()][0], this.battleButtons[this.key.getCmdNum()][1]);
		}
		
		
	}
	
	public void battleText(String message) {
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		brush.drawString("* "+message, 16*3, 176*3);
		
	}
	//
	
	
	
	
	
	
	
	public void playerMenu() {
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		brush.setColor(Color.black);
		brush.fillRoundRect(48,48*2,48*3,48*11,10,10);
		
		brush.setColor(Color.white);
		brush.drawString("STATS", 48+25, 48*3);
		brush.drawString("BOLSA", 48+25, 48*4);
		brush.drawString("NIVEL: ", 48+25, 48*11);
		brush.setColor(Color.yellow);
		brush.drawString("OURO: ", 48+25, 48*12);
		
		if (this.playerMenuState.equals("main")) {
			
			if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 1) {
				this.key.correctCmdNum();
			}
			
			brush.drawString(">",this.pMenuMainButtons[this.key.getCmdNum()][0],
								 this.pMenuMainButtons[this.key.getCmdNum()][1]);
		}
		
	}
	
	
	
	
	
	
	
	
	
	// TESTE(COORDENADAS)
	public void draw(int x, int y) {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("wX: " + x + " wY: " + y, 48, 48);
		
	}
	//

	public void setBattleUI() {
		try {
			this.battleUI = ImageIO.read(getClass().getResourceAsStream("/battle/battleUI.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setBrush(Graphics2D brush) {
		this.brush = brush;
	}


}