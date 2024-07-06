package main.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import combat.Spells;
import entities.Entity;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import entities.player.Player;
import entities.teammates.Team;
import entities.teammates.Teammate;
import exceptions.IndexOutOfRangeException;
import items.Inventory;
import items.Item;
import items.Stock;

import main.KeyInput;

import states.Battle;
import states.PlayerMenu;
import states.Shop;

public class UI {
	
	private Font font; //= new Font("Arial", Font.PLAIN, 20);
	
	private BufferedImage battleUI;
	private BufferedImage[] effects = new BufferedImage[6];
	
	private Graphics2D brush;
	
	private final KeyInput key;
	
	private int battleButtonInitX = 475;
	private int battleButtonInitY = 550;
	
	private int[][] pMenuMainButtons = {{48+20-10, 48*3},{48+20-10, 48*4}};
	private int[][] pMenuInventoryButtons = new int[11][2];
	private int[][] pMenuItemButtons = {{48*9+20+48-15, 48*2+229+48*4+15},
										{48*9+20+48-15, 48*2+229+48*5+15-24},
										{48*9+20+48-15, 48*2+229+48*5+15}};
	private int[][] chooseCharacterButtons;
	
	
	private int[][] battleButtons = {{battleButtonInitX-15, battleButtonInitY}, 
									 {battleButtonInitX-15+111, battleButtonInitY},
									 {battleButtonInitX-15, battleButtonInitY+48}, 
									 {battleButtonInitX-15+111, battleButtonInitY+48}, 
									 {battleButtonInitX-15, battleButtonInitY+48+48}, 
									 {battleButtonInitX-15+111, battleButtonInitY+48+48}};
	
	
	private int[][] shopButtons;
	private int[][] productButtons= {{48*11-15, 48*2+229+48*4+15},
									{48*11-15, 48*2+229+48*5+15-24}};
	
	private int[] rainbow = {0,0,255};
	private int rainbowState = 1;
	
	public UI(KeyInput key) {
		
		setBattleUI();
		setEffects();
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
	public void battleScreen(Player player, Teammate[] teammates, Enemie enemie, Battle battle) {
		
		Stats playerStats = player.getStats();
		Stats enemieStats = enemie.getStats();
		
		Stats teammate1Stats = teammates[0].getStats();
		Stats teammate2Stats = teammates[1].getStats();
		Stats teammate3Stats = teammates[2].getStats();
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(0, 0, 720, 720, 10, 10);
		brush.drawImage(this.battleUI, 0, 0, 720, 720, null);
		
		brush.setFont(font.deriveFont(Font.PLAIN, 15F));
		
		brush.setColor(Color.red);
		brush.fillRect(64, 233, 96, 5);
		brush.fillRect(64, 275+15, 96, 5);
		brush.fillRect(64, 317+30, 96, 5);
		brush.fillRect(64, 359+45, 96, 5);
		brush.fillRect(568, 41, 96, 5);
		
		
		brush.setColor(Color.white);
		brush.drawString("VOCE: ", 24, 240-15);
		brush.drawString(teammates[0].getName()+": ", 24, 282);
		brush.drawString(teammates[1].getName()+": ", 24, 324+15);
		brush.drawString(teammates[2].getName()+": ", 24, 366+30);
		
		brush.setColor(Color.green);
		brush.drawString("HP: ", 24, 240);
		brush.fillRect(64, 233, 96*playerStats.getHealth()/playerStats.getMaxHealth(), 5);
		
		brush.drawString("HP: ", 24, 282+15);
		brush.fillRect(64, 275+15, 96*teammate1Stats.getHealth()/teammate1Stats.getMaxHealth(), 5);
		
		brush.drawString("HP: ", 24, 324+30);
		brush.fillRect(64, 317+30, 96*teammate2Stats.getHealth()/teammate2Stats.getMaxHealth(), 5);
		
		brush.drawString("HP: ", 24, 366+45);
		brush.fillRect(64, 359+45, 96*teammate3Stats.getHealth()/teammate3Stats.getMaxHealth(), 5);
		
		brush.drawString("HP: ", 528, 48);
		brush.fillRect(568, 41, 96*enemieStats.getHealth()/enemieStats.getMaxHealth(), 5);
		
		brush.setColor(Color.magenta);
		brush.drawString("MP: " + playerStats.getMana() +"/"+ playerStats.getMaxMana(), 24, 261);
		brush.drawString("MP: " + teammate1Stats.getMana() +"/"+ teammate1Stats.getMaxMana(), 24, 303+15);
		brush.drawString("MP: " + teammate2Stats.getMana() +"/"+ teammate2Stats.getMaxMana(), 24, 345+30);
		brush.drawString("MP: " + teammate3Stats.getMana() +"/"+ teammate3Stats.getMaxMana(), 24, 387+45);
		
		battleOptionsBox(player, battle);
		effect(player.getEffects().getCurrentEffect(),enemie.getEffects().getCurrentEffect());
		
	}
	
	private void battleOptionsBox(Player player, Battle battle) {
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		
		String battleState = battle.getBattleState();
		
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
			
		} else if (battleState.equals("choose-item")) {
		
			Inventory inventory = player.getInventory();
			int inventoryPage = battle.getInventoryPage();
			
			for (int i = 4*(inventoryPage-1); i < 4*inventoryPage; i++) {
				
				Item item;
				int index = i - 4*(inventoryPage-1);
				
				try {
					
					if (i < 10) {
						item = inventory.getItem(i);
						String itemName = (item != null) ? item.getName(): "-";
						brush.drawString(itemName, battleButtons[index][0]+15, battleButtons[index][1]);
					} else {
						brush.drawString("-", battleButtons[index][0]+15, battleButtons[index][1]);
					}
					
				} catch (IndexOutOfRangeException e) {
					e.printStackTrace();
				}
				
			}
			
			brush.drawString("VOLTAR", battleButtonInitX, battleButtonInitY+48+48); // cmdNum = 4
			brush.drawString("-->", battleButtonInitX+111, battleButtonInitY+48+48);  // cmdNum = 5
			
		}
		
		if (!battleState.equals("enemie-turn") && !battleState.equals("enemie-text")) {
			brush.drawString(">", this.battleButtons[this.key.getCmdNum()][0],
								  this.battleButtons[this.key.getCmdNum()][1]);
		}
		
		
	}
	
	private void effect(String pEffect,String eEffect) {
		
		BufferedImage playerEffect;
		BufferedImage enemieEffect;
		
		String file;
		
		try {
			
			if (!pEffect.equals("none")) {
				file = "/effects/"+pEffect+".png";
				playerEffect = ImageIO.read(getClass().getResourceAsStream(file));
				brush.drawImage(playerEffect, 96*3, 128*3-8, 8, 8, null);
			}
			
			if (!eEffect.equals("none")) {
				file = "/effects/"+eEffect+".png";
				enemieEffect = ImageIO.read(getClass().getResourceAsStream(file));
				brush.drawImage(enemieEffect, 104*3, 40*3-16, 16, 16, null);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void battleText(String message) {
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		brush.drawString("* "+message, 16*3, 176*3);
		
	}
	//
	
	public void rainbowStuff() {
		
		if(rainbowState == 1) {
			rainbow[0]++;
			rainbow[2]--;
			if (rainbow[0] == 255) {
				this.rainbowState = 2;
			}
		} else if (rainbowState == 2){
			rainbow[1]++;
			rainbow[0]--;
			if (rainbow[1] == 255) {
				this.rainbowState = 3;
			}
		} else if (rainbowState == 3) {
			rainbow[2]++;
			rainbow[1]--;
			if (rainbow[2] == 255) {
				this.rainbowState = 1;
			}
		}
		
	}
	
	// MENU DO PLAYER
	public void playerMenu(PlayerMenu pMenu, Player player, Teammate[] teammates) {
		
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		brush.setColor(Color.black);
		brush.fillRoundRect(48,48*2,48*3,48*11,10,10);
		
		brush.setColor(Color.white);
		brush.drawString("STATS", 48+25, 48*3);
		brush.drawString("BOLSA", 48+25, 48*4);
		brush.drawString("NIVEL: "+player.getLevel(), 48+25, 48*11);
		brush.setColor(Color.yellow);
		brush.drawString("OURO: "+player.getGold(), 48+25, 48*12);
		
		Item itemSelected = pMenu.getItemSelected();
		String pMenuState = pMenu.getState();
		Inventory inventory = player.getInventory();
		
		if (pMenuState.equals("main")) {
			
			if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 1) {
				this.key.correctCmdNum();
			}
			
			brush.drawString(">",this.pMenuMainButtons[this.key.getCmdNum()][0],
								 this.pMenuMainButtons[this.key.getCmdNum()][1]);
			
			
		} 
		
		else if (pMenuState.equals("stats")) {
			
			statsMenu(player, teammates, pMenuState);
			
		}
		
		else if (pMenuState.equals("inventory")) {
			
			inventoryMenu(itemSelected, inventory);
			
			if (itemSelected != null) {
				
				selectedItem(itemSelected, inventory);
	
			}
				
		} else if (pMenuState.equals("choose-character")) {
			
			chooseCharacter(player, teammates, pMenuState);
			
		}
	}
	public void statsMenu(Player player, Teammate[] teammates, String pMenuState) {
		
		rainbowStuff();
		brush.setColor(Color.black);
		brush.fillRoundRect(48*4+10,48*2,48*8,48*11,10,10);
		
		Team[] team = {player,teammates[0],teammates[1],teammates[2]};
		int firstCharBoxX = 235;
		int charBoxSide = 48+24+6;
		
		for (int i = 0; i < 4; i++) {
			brush.setColor(new Color(rainbow[0]/2,rainbow[1]/2,rainbow[2]/2));
			brush.fillRoundRect(firstCharBoxX+82*i-3, 48*3-3, charBoxSide, charBoxSide,10,10);
			brush.drawImage(team[i].getIdleSprites()[1], firstCharBoxX+82*i, 48*3, charBoxSide-6, charBoxSide-6, null);
		
			brush.setColor(Color.red);
			brush.fillRect(firstCharBoxX+82*i+charBoxSide/2-15, 48*8, 5, 48*3);
			brush.setColor(Color.green);
			brush.fillRect(firstCharBoxX+82*i+charBoxSide/2-15, 48*8, 5, 48*3*team[i].getStats().getHealth()/team[i].getStats().getMaxHealth());
			brush.setColor(Color.magenta);
			brush.fillRect(firstCharBoxX+82*i+10+charBoxSide/2-15, 48*8, 5, 48*2*team[i].getStats().getMana()/team[i].getStats().getMaxMana());
		}
		
		BufferedImage armorIcon = null;
		BufferedImage weaponIcon = null;
		try {
			armorIcon = ImageIO.read(getClass().getResourceAsStream("/equipables/armor.png"));
			weaponIcon = ImageIO.read(getClass().getResourceAsStream("/equipables/weapon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		brush.setFont(font.deriveFont(Font.PLAIN, 10F));
		brush.setColor(Color.white);
		
		for (int i = 0; i < 4; i++) {
			
			brush.drawImage(armorIcon, firstCharBoxX-3+82*i, 48*5, 16, 16, null);
			brush.drawImage(weaponIcon, firstCharBoxX-3+82*i, 48*5+24, 16, 16, null);
			
			Item armorEquiped = team[i].getArmorEquiped();
			Item weaponEquiped = team[i].getWeaponEquiped();
			
			if (armorEquiped != null) {
				
				brush.drawString(armorEquiped.getShortName(),firstCharBoxX+20+82*i, 48*5+12);
				
			} else {
				brush.drawString("-",firstCharBoxX+20+82*i, 48*5+12);
			}
			if (weaponEquiped != null) {
				
				brush.drawString(team[i].getWeaponEquiped().getShortName(),firstCharBoxX+20+82*i, 48*5+24+12);
				System.out.println(team[0].getWeaponEquiped().getShortName());
				System.out.println(weaponEquiped.getShortName());
				
			} else {
				brush.drawString("-",firstCharBoxX+20+82*i, 48*5+24+12);
			}
			
			
			
			Stats stats = team[i].getStats();
			
			brush.drawString("ATQ: "+stats.getStrenght(), firstCharBoxX+82*i, 48*6+20);
			brush.drawString("DEF: "+stats.getDefense(), firstCharBoxX+82*i, 48*6+30);
			brush.drawString("AGL: "+stats.getAgility(), firstCharBoxX+82*i, 48*6+40);
			brush.drawString("CRT: "+stats.getCriticalDamage()+"%", firstCharBoxX+82*i, 48*6+50);
			brush.drawString("MGC: "+stats.getMagic(), firstCharBoxX+82*i, 48*6+60);
			brush.drawString("MGD: "+stats.getMagicDefense(), firstCharBoxX+82*i, 48*6+70);
			
		}
		
		
		
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		
		if (!pMenuState.equals("choose-character")) {
			
			brush.setColor(Color.white);
			brush.drawString("VOLTAR", 48*4+35, 48*3+40*11);
			brush.setColor(Color.yellow);
			brush.drawString(">",48*4+35-15, 48*3+40*11);
			
		} else {
			
			if (this.chooseCharacterButtons == null) {
				this.chooseCharacterButtons = new int[4][2];
				for (int i = 0; i < 4; i++) {
					this.chooseCharacterButtons[i][0] = firstCharBoxX+82*i-9 + charBoxSide/2;
					this.chooseCharacterButtons[i][1] = 48*3-10;
				}
			}
			
			brush.setColor(Color.yellow);
			if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 3) {
				this.key.correctCmdNum();
			}
			
			brush.drawString("V",this.chooseCharacterButtons[this.key.getCmdNum()][0],
		 			 			this.chooseCharacterButtons[this.key.getCmdNum()][1]);
		}
		
	}
	public void inventoryMenu(Item itemSelected, Inventory inventory) {
		
		brush.setColor(Color.black);
		brush.fillRoundRect(48*4+10,48*2,48*5,48*11,10,10);
		brush.fillRoundRect(48*9+20,48*2,219,219,10,10);
		
		brush.setColor(Color.white);
		
		for(int i = 0; i < 10; i++) {
			
			if (this.pMenuInventoryButtons[i][0] == 0 && this.pMenuInventoryButtons[i][1] == 0 ) {
				this.pMenuInventoryButtons[i][0] = 48*4+35-15;
				this.pMenuInventoryButtons[i][1] = 48*3+40*i;
			}

			Item item;
			try {
				item = inventory.getItem(i);
				String itemName = (item != null) ? item.getName() : "";
				brush.drawString("- "+itemName, 48*4+35, 48*3+40*i);
			} catch (IndexOutOfRangeException e) {
				e.printStackTrace();
			}
			
		}
		
		this.pMenuInventoryButtons[10][0] = 48*4+35-15;
		this.pMenuInventoryButtons[10][1] = 48*3+40*11;
		brush.drawString("VOLTAR", 48*4+35, 48*3+40*11);
		
		if(itemSelected == null) {
			
			if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 10) {
				this.key.correctCmdNum();
			}
			
			brush.setColor(Color.yellow);
			brush.drawString(">",this.pMenuInventoryButtons[this.key.getCmdNum()][0],
					 			 this.pMenuInventoryButtons[this.key.getCmdNum()][1]);
			
		}
		
	}
	public void selectedItem(Item itemSelected, Inventory inventory) {
		
		brush.setColor(Color.black);
		brush.fillRoundRect(48*9+20, 48*2+229,219, 48*11-229, 10,10);
		
		
		brush.setColor(Color.white);
		
		if (itemSelected.isUsable()) {
			brush.drawString("USAR", 48*9+20+48, 48*2+229+48*4+15);
		} else if (itemSelected.isEquipable()) {
			brush.drawString("EQUIPAR", 48*9+20+48, 48*2+229+48*4+15);
		}
		brush.drawString("LARGAR", 48*9+20+48, 48*2+229+48*5+15-24);
		
		brush.drawString("VOLTAR", 48*9+20+48, 48*2+229+48*5+15);
		
		if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 2) {
			this.key.correctCmdNum();
		}
		
		brush.drawString(">",this.pMenuItemButtons[this.key.getCmdNum()][0],
	 			 			this.pMenuItemButtons[this.key.getCmdNum()][1]);
		
	}
	
	public void chooseCharacter(Player player, Teammate[] teammates, String pMenuState) {
		this.key.setButtonCols(4);
		statsMenu(player, teammates, pMenuState);
	}
	
	//
	
	// LOJA
	public void shopScreen(Stock stock, Shop shop, int gold) {
		
		if (this.shopButtons == null) {
			shopButtons = new int[5][2];
			for (int i = 0; i < 5; i++) {
				shopButtons[i][0] = 48+25-15;
				shopButtons[i][1] = 48*(3+i);
			}
		}
		
		brush.setColor(Color.black);
		brush.fillRoundRect(48,48*2,48*4,48*11,10,10);
		brush.fillRoundRect(48*10,48*2,48*4,48*11,10,10);
		
		brush.setFont(font.deriveFont(Font.PLAIN, 10F));
		brush.setColor(Color.white);
		
		
		int index = 0;
		Item item;
		try {
			item = stock.getItem(index);
			while (item != null) {
				brush.drawString("-"+item.getName()+" x"+stock.getAmount(index), 48+25, 48*(3+index));
				index++;
				item = stock.getItem(index);
			}
		} catch (IndexOutOfRangeException e) {
			System.out.println(index);
			e.printStackTrace();
		}
		
		if (shop.getShopState().equals("choose-item")) {
			
			if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > index-1) {
				this.key.correctCmdNum();
			}
			
			brush.setFont(font.deriveFont(Font.PLAIN, 16F));
			brush.setColor(Color.yellow);
			brush.drawString(">", this.shopButtons[this.key.getCmdNum()][0],
					  			this.shopButtons[this.key.getCmdNum()][1]);
			
		} else if (shop.getShopState().equals("buying")) {
			
			brush.setFont(font.deriveFont(Font.PLAIN, 16F));
			brush.drawString("COMPRAR",this.productButtons[0][0]+15,this.productButtons[0][1]);
			brush.drawString("VOLTAR",this.productButtons[1][0]+15,this.productButtons[1][1]);
			
			if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 1) {
				this.key.correctCmdNum();
			}
			
			brush.setColor(Color.yellow);
			brush.drawString(">", this.productButtons[this.key.getCmdNum()][0],
					  			this.productButtons[this.key.getCmdNum()][1]);
			
		}
		
		
		
	}
	
	//
	
	
	
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

	public void setEffects() {
		try {
			this.effects[0] = ImageIO.read(getClass().getResourceAsStream("/effects/burning.png"));
			this.effects[1] = ImageIO.read(getClass().getResourceAsStream("/effects/paralyzed.png"));
			this.effects[2] = ImageIO.read(getClass().getResourceAsStream("/effects/poisoned.png"));
			this.effects[3] = ImageIO.read(getClass().getResourceAsStream("/effects/hypnotized.png"));
			this.effects[4] = ImageIO.read(getClass().getResourceAsStream("/effects/bleeding.png"));
			this.effects[5] = ImageIO.read(getClass().getResourceAsStream("/effects/cursed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}