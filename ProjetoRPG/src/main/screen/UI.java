package main.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import combat.spells.KnownSpells;
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
	
	private Font font;
	
	private BufferedImage battleUI;
	
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
	private int[][] spellSlotButtons;
	
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
	
	private int textCount = 0;
	private int textTimer = 0;
	private String textBefore = "";
	
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
		effect(player,teammates,enemie);
		
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
			
			KnownSpells spells = player.getSpells();
			
			brush.setFont(font.deriveFont(Font.PLAIN, 18F));
			
			String spellShortName;
			for (int i = 0; i < 5; i++) {
				spellShortName = (spells.getSpell(i+1) != null) ? spells.getSpell(i+1).getShortSpellName():"-";
				brush.drawString(spellShortName, battleButtonInitX+111*(i%2), battleButtonInitY+48*(i/2)); 
			}
			
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
		
		if (battleState.split("-")[0].equals("choose")) {
			this.displayArrow(battleButtons, 5);
		}
		
		/*if (!battleState.equals("enemie-turn") && !battleState.equals("enemie-text")) {
			brush.drawString(">", this.battleButtons[this.key.getCmdNum()][0],
								  this.battleButtons[this.key.getCmdNum()][1]);
		}*/
		
		
	}
	
	private void effect(Player player, Teammate[] teammates, Enemie enemie) {
		
		BufferedImage effectIcon;
		
		String playerCurrentEffect = player.getEffects().getCurrentEffect();
		String teammate1CurrentEffect = teammates[0].getEffects().getCurrentEffect();
		String teammate2CurrentEffect = teammates[1].getEffects().getCurrentEffect();
		String teammate3CurrentEffect = teammates[2].getEffects().getCurrentEffect();
		String enemieCurrentEffect = enemie.getEffects().getCurrentEffect();
		
		String file;
		
		try {
			if (!playerCurrentEffect.equals("none")) {
				file = "/effects/"+playerCurrentEffect+".png";
				effectIcon = ImageIO.read(getClass().getResourceAsStream(file));
				brush.drawImage(effectIcon, 48*6, 128*3-8, 8, 8, null);
			}
			if (!teammate1CurrentEffect.equals("none")) {
				file = "/effects/"+teammate1CurrentEffect+".png";
				effectIcon = ImageIO.read(getClass().getResourceAsStream(file));
				brush.drawImage(effectIcon, 48*4+24, 48*7-8, 8, 8, null);
			}
			if (!teammate2CurrentEffect.equals("none")) {
				file = "/effects/"+teammate2CurrentEffect+".png";
				effectIcon = ImageIO.read(getClass().getResourceAsStream(file));
				brush.drawImage(effectIcon, 48*8, 48*8-8, 8, 8, null);
			}
			if (!teammate3CurrentEffect.equals("none")) {
				file = "/effects/"+teammate3CurrentEffect+".png";
				effectIcon = ImageIO.read(getClass().getResourceAsStream(file));
				brush.drawImage(effectIcon, 48*10-24, 48*7-8, 8, 8, null);
			}
			if (!enemieCurrentEffect.equals("none")) {
				file = "/effects/"+enemieCurrentEffect+".png";
				effectIcon = ImageIO.read(getClass().getResourceAsStream(file));
				brush.drawImage(effectIcon, 104*3, 40*3-16, 16, 16, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void battleText(String message) {
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		this.displayText("* "+message, 27);
		
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
	
	private void displayArrow(int[][] buttons, int maxIndex) {
		brush.setColor(Color.yellow);
		if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > maxIndex) {
			this.key.correctCmdNum();
		}
		brush.drawString(">",buttons[this.key.getCmdNum()][0],
							 buttons[this.key.getCmdNum()][1]);
	}
	
	private void displayText(String text, int width) {
		
		if (!this.textBefore.equals(text)) {
			this.textCount = 0;
		}
		
		this.textBefore = text;
		
		String[] letters = text.split("");
		int skipLine = 0;
		int letter = 0;
		
		for (int i = 0; i < this.textCount; i++) {
			letter = i;
			if (i >= width && i < 2*width) {
				skipLine = 1;
				letter = i-27;
			} else if (i >= 2*width) {
				skipLine = 2;
				letter = i-27*2;
			}
			brush.drawString(letters[i], 16*3+14*letter, 176*3+24*skipLine);
		}
		
		textTimer++;
		if (textTimer >= 3) {
			textCount++;
			textTimer = 0;
		}
		if (textCount >= letters.length) {
			textCount = letters.length;
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
			displayArrow(this.pMenuMainButtons, 1);
		} 
		else if (pMenuState.equals("stats")) {
			statsMenu(player, teammates, pMenuState);
		}
		else if (pMenuState.equals("inventory")) {
			inventoryMenu(itemSelected, inventory);
			if (itemSelected != null) {
				selectedItem(itemSelected, inventory);
			}
		}
		else if (pMenuState.equals("choose-character")) {
			chooseCharacter(player, teammates, pMenuState);
		}
		else if (pMenuState.equals("choose-spellSlot")) {
			chooseSpellSlot(player, teammates, pMenuState);
		}
	}
	public void statsMenu(Player player, Teammate[] teammates, String pMenuState) {
		
		rainbowStuff();
		brush.setColor(Color.black);
		brush.fillRoundRect(48*4+10,48*2,48*8,48*11,10,10);
		
		Team[] team = {player,teammates[0],teammates[1],teammates[2]};
		int firstCharBoxX = 235;
		int charBoxSide = 48+24+6;
		
		BufferedImage armorIcon = null;
		BufferedImage weaponIcon = null;
		try {
			armorIcon = ImageIO.read(getClass().getResourceAsStream("/equipables/armor.png"));
			weaponIcon = ImageIO.read(getClass().getResourceAsStream("/equipables/weapon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 4; i++) {
			
			brush.setColor(new Color(rainbow[0]/2,rainbow[1]/2,rainbow[2]/2));
			brush.fillRoundRect(firstCharBoxX+82*i-3, 48*3-3, charBoxSide, charBoxSide,10,10);
			brush.drawImage(team[i].getIdleSprites()[1], firstCharBoxX+82*i, 48*3, charBoxSide-6, charBoxSide-6, null);
		
			int lifeBar = 48*3*team[i].getStats().getHealth()/team[i].getStats().getMaxHealth();
			int manaBar = 48*2*team[i].getStats().getMana()/team[i].getStats().getMaxMana();
			
			brush.setColor(Color.red);
			brush.fillRect(firstCharBoxX+82*i+charBoxSide/2-15, 48*8, 5, 48*3);
			brush.setColor(Color.green);
			brush.fillRect(firstCharBoxX+82*i+charBoxSide/2-15, 48*8, 5, lifeBar);
			brush.setColor(Color.magenta);
			brush.fillRect(firstCharBoxX+82*i+10+charBoxSide/2-15, 48*8, 5, manaBar);
			
			brush.drawImage(armorIcon, firstCharBoxX-3+82*i, 48*5, 16, 16, null);
			brush.drawImage(weaponIcon, firstCharBoxX-3+82*i, 48*5+24, 16, 16, null);
			
			Item armorEquiped = team[i].getArmorEquiped();
			Item weaponEquiped = team[i].getWeaponEquiped();
			
			brush.setFont(font.deriveFont(Font.PLAIN, 10F));
			brush.setColor(Color.white);
			
			if (armorEquiped != null) {
				brush.drawString(armorEquiped.getShortName(),firstCharBoxX+20+82*i, 48*5+12);
			} else {
				brush.drawString("-",firstCharBoxX+20+82*i, 48*5+12);
			}
			if (weaponEquiped != null) {
				brush.drawString(weaponEquiped.getShortName(),firstCharBoxX+20+82*i, 48*5+24+12);
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
			
			KnownSpells spells = team[i].getSpells();
			
			String spellShortName;
			for (int j = 0; j < 5; j++) {
				spellShortName = (spells.getSpell(j+1) != null) ? spells.getSpell(j+1).getShortSpellName():"-";
				brush.drawString(spellShortName, firstCharBoxX+82*i, 48*11+5+10*(j+1)); // cmdNum = 0
			}
		}
		
		brush.setColor(Color.magenta);
		brush.drawString("M: ", firstCharBoxX-24, 48*11+35);
		
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		
		  if (pMenuState.equals("choose-character")){
			
			if (this.chooseCharacterButtons == null) {
				this.chooseCharacterButtons = new int[4][2];
				for (int i = 0; i < 4; i++) {
					this.chooseCharacterButtons[i][0] = firstCharBoxX+82*i-9 + charBoxSide/2;
					this.chooseCharacterButtons[i][1] = 48*3-10;
				}
			}
			
			displayArrow(this.chooseCharacterButtons, 3);
			
		} else if (pMenuState.equals("choose-spellSlot")) {
			
			if (this.spellSlotButtons == null) {
				this.spellSlotButtons = new int[5][2];
				for (int i = 0; i < 5; i++) {
					this.spellSlotButtons[i][0] = firstCharBoxX+82*this.key.getCmdNum()-9;
					this.spellSlotButtons[i][1] = 48*11+7+10*(i+1);
				}
			}
			
			displayArrow(this.spellSlotButtons, 4);
			
		} else {
			
			if (this.spellSlotButtons != null) {
				this.spellSlotButtons = null;
			}
			
			brush.setColor(Color.white);
			brush.drawString("VOLTAR", 48*4+35, 40*15);
			brush.setColor(Color.yellow);
			brush.drawString(">",48*4+35-15, 40*15);
			
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
		
		if (itemSelected == null) {
			displayArrow(this.pMenuInventoryButtons, 10);
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
		
		displayArrow(this.pMenuItemButtons, 2);
		
	}
	
	public void chooseCharacter(Player player, Teammate[] teammates, String pMenuState) {
		this.key.setButtonCols(4);
		statsMenu(player, teammates, pMenuState);
	}
	public void chooseSpellSlot(Player player, Teammate[] teammates, String pMenuState) {
		this.key.setButtonCols(1);
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
			
			brush.setFont(font.deriveFont(Font.PLAIN, 16F));
			displayArrow(this.shopButtons, index-1);
			
		} else if (shop.getShopState().equals("buying")) {
			
			brush.setFont(font.deriveFont(Font.PLAIN, 16F));
			brush.drawString("COMPRAR",this.productButtons[0][0]+15,this.productButtons[0][1]);
			brush.drawString("VOLTAR",this.productButtons[1][0]+15,this.productButtons[1][1]);
			
			displayArrow(this.shopButtons, 1);
			
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


}