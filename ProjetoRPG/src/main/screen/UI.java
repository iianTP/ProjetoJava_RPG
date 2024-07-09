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
import quests.Quest;
import quests.QuestList;
import states.Battle;
import states.Dialogue;
import states.PlayerMenu;
import states.Shop;

public class UI {
	
	private Font font;
	
	private BufferedImage battleUI;
	
	private Graphics2D brush;
	
	private final KeyInput key;
	
	private int battleButtonInitX = 475;
	private int battleButtonInitY = 550;
	
	private int[][] pMenuMainButtons = {{48+10, 48*3},{48+10, 48*4},{48+10, 48*5},{48+10, 48*6}};
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
	
	
	private int[][] shopActButtons = {{48+10, 48*3},{48+10, 48*4},{48+10, 48*5}};
	private int[][] shopItemsButtons;
	private int[][] productButtons= {{48*11-15, 48*2+229+48*4+15},
									{48*11-15, 48*2+229+48*5+15-24}};
	
	private int[] rainbow = {0,0,255};
	private int rainbowState = 1;
	
	private int textCount = 0;
	private int textTimer = 0;
	private boolean endedText = false;
	
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
	
	public void resetTextProps() {
		this.textCount = 0;
		this.textTimer = 0;
		this.endedText = false;
	}
	
	// PAUSE
	public void pauseScreen() {
		
		brush.setFont(font);
		brush.setColor(Color.black);
		brush.drawString("PAUSE", 550, 48);
		
	}
	
	// DIALOGO
	public void dialogue(Dialogue dialogue) {
		
		brush.setColor(new Color(0,0,0,200));
		brush.fillRoundRect(48, 48, 48*13, 48*4, 10, 10);
		
		brush.setFont(font);
		brush.setColor(Color.white);
		
		this.displayText(dialogue.getDialogue(), 50, 48+24, 48*2);
		
		if (dialogue.isDialogueChanged()) {
			this.endedText = false;
			this.textCount = 0;
		}
		
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
		
		brush.drawImage(player.getIdleSprites()[0], 48*6, 48*8, 48, 48, null);
		brush.drawImage(teammates[0].getIdleSprites()[0], 48*4+24, 48*7, 48, 48, null);
		brush.drawImage(teammates[1].getIdleSprites()[0], 48*8, 48*8, 48, 48, null);
		brush.drawImage(teammates[2].getIdleSprites()[0], 48*10-24, 48*7, 48, 48, null);
		brush.drawImage(enemie.getSprite(), 104*3, 40*3, 48*2, 48*2, null);
		
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
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		
		if (battle.isChangedBattleState()) {
			this.endedText = false;
			this.textCount = 0;
		}
		
		displayText("* "+battle.getMessage(), 25, 16*3, 176*3);
		
	}
	
	private void battleOptionsBox(Player player, Battle battle) {
		
		brush.setFont(this.font.deriveFont(Font.PLAIN, 18F));
		brush.setColor(Color.white);
		
		String battleState = battle.getBattleState();
		
		if (battleState.equals("choose-move")) {
			battleChooseMove();
		} else if (battleState.equals("choose-spell")) {
			battleChooseSpell(player.getSpells());
		} else if (battleState.equals("choose-item")) {
		
			battleChooseItem(player.getInventory(), battle.getInventoryPage());
		}
		
		if (battleState.split("-")[0].equals("choose")) {
			this.displayArrow(this.battleButtons, 5);
		}
		
	}
	
	private void battleChooseMove() {
		brush.drawString("ATAQUE", battleButtonInitX, battleButtonInitY); // cmdNum = 0
		brush.drawString("DEFESA", battleButtonInitX+111, battleButtonInitY); // cmdNum = 1
		brush.drawString("MAGIA", battleButtonInitX, battleButtonInitY+48);  // cmdNum = 2
		brush.drawString("BOLSA", battleButtonInitX+111, battleButtonInitY+48);  // cmdNum = 3
		brush.drawString("ESPEC.", battleButtonInitX, battleButtonInitY+48+48); // cmdNum = 4
		brush.drawString("FUGIR", battleButtonInitX+111, battleButtonInitY+48+48);  // cmdNum = 5
	}
	
	private void battleChooseSpell(KnownSpells knownSpells) {
		KnownSpells spells = knownSpells;
		
		brush.setFont(font.deriveFont(Font.PLAIN, 18F));
		
		String spellShortName;
		for (int i = 0; i < 5; i++) {
			spellShortName = (spells.getSpell(i+1) != null) ? spells.getSpell(i+1).getShortSpellName():"-";
			brush.drawString(spellShortName, battleButtonInitX+111*(i%2), battleButtonInitY+48*(i/2)); 
		}
		
		brush.drawString("VOLTAR", battleButtonInitX+111, battleButtonInitY+48+48);  // cmdNum = 5
	}
	
	private void battleChooseItem(Inventory playerInventory, int battleInventoryPage) {
		Inventory inventory = playerInventory;
		int inventoryPage = battleInventoryPage;
		
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
	
	private void displayText(String text, int width, int startX, int startY) {
		
		String[] letters = text.split("");
		int skipLine = 0;
		int letter;
		
		for (int i = 0; i < this.textCount; i++) {
			letter = i;
			if (i >= width && i < 2*width) {
				skipLine = 1;
				letter = i-width;
			} else if (i >= 2*width) {
				skipLine = 2;
				letter = i-width*2;
			}
			
			brush.drawString(letters[i], startX+14*letter, startY+24*skipLine);
		}
		
		if (this.endedText) {
			textCount = letters.length;
		} else {
			textTimer++;
			if (textTimer >= 2) {
				textCount++;
				textTimer = 0;
			}
			if (textCount >= letters.length) {
				this.endedText = true;
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
		brush.drawString("MISSOES", 48+25, 48*5);
		brush.drawString("SAIR", 48+25, 48*6);
		brush.drawString("NIVEL: "+player.getLevel(), 48+25, 48*11);
		brush.setColor(Color.yellow);
		brush.drawString("OURO: "+player.getGold(), 48+25, 48*12);
		
		Item itemSelected = pMenu.getItemSelected();
		String pMenuState = pMenu.getState();
		Inventory inventory = player.getInventory();
		
		if (pMenuState.equals("main")) {
			displayArrow(this.pMenuMainButtons, 3);
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
		else if (pMenuState.equals("quests")) {
			this.questMenu(player.getQuestList());
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
	private void inventoryMenu(Item itemSelected, Inventory inventory) {
		
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
	
	private void questMenu(QuestList quests) {
		brush.setColor(Color.black);
		brush.fillRoundRect(48*4+10,48*2,48*10,48*11,10,10);

		int index = 0;
		Quest quest = quests.getQuest(index);
		
		brush.setFont(font.deriveFont(Font.PLAIN, 14F));
		while (quest != null) {
			brush.setColor(Color.white);
			brush.drawString("TIPO: "+quest.getType(), 48*4+35, 48*3+100*index);
			brush.drawString("DESCRICAO: "+quest.getDescription(), 48*4+35, 48*3+20+100*index);
			brush.drawString("RECOMPENSA: "+quest.getReward().getRewardString(), 48*4+35, 48*3+40+100*index);
			if (quest.isDone()) {
				brush.setColor(Color.green);
				brush.drawString("FEITO", 48*4+35, 48*3+60+100*index);
			} else {
				brush.setColor(Color.yellow);
				brush.drawString("EM ANDAMENTO", 48*4+35, 48*3+60+100*index);
			}
			index++;
			quest = quests.getQuest(index);
		}
		
	}
	
	private void selectedItem(Item itemSelected, Inventory inventory) {
		
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
	private void chooseCharacter(Player player, Teammate[] teammates, String pMenuState) {
		this.key.setButtonCols(4);
		statsMenu(player, teammates, pMenuState);
	}
	private void chooseSpellSlot(Player player, Teammate[] teammates, String pMenuState) {
		this.key.setButtonCols(1);
		statsMenu(player, teammates, pMenuState);
	}
	
	//
	
	// LOJA
	public void shopScreen(Stock stock, Shop shop, Player player) {
		
		this.shopItemsButtons = null;
		
		brush.setColor(Color.black);
		brush.fillRoundRect(48,48*2,48*3,48*11,10,10);
		
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		brush.setColor(Color.white);
		
		brush.drawString("COMPRAR", 48+25, 48*3);
		brush.drawString("VENDER", 48+25, 48*4);
		brush.drawString("SAIR", 48+25, 48*5);
		
		brush.setColor(Color.yellow);
		brush.drawString("OURO: "+player.getGold(), 48+25, 48*12);
		
		
		if (shop.getShopState().equals("choose-act")) {
			displayArrow(this.shopActButtons, 2);
		} else if (shop.getShopState().equals("choose-item")) {
			shopChooseItem(stock, shop.getSelectedButton());
		} else if (shop.getShopState().equals("sell-item")) {
			shopChooseItemToSell(player.getInventory(), shop.getSelectedButton());
		}
		
	}
	
	private void shopChooseItem(Stock stock, int selectedButton) {
		
		brush.setColor(Color.black);
		brush.fillRoundRect(48*4+10,48*2,48*5,48*11,10,10);
		brush.fillRoundRect(48*9+20,48*2,219,219,10,10);
		
		if (this.shopItemsButtons == null) {
			shopItemsButtons = new int[stock.getStockSize()+1][2];
			for (int i = 0; i < stock.getStockSize(); i++) {
				shopItemsButtons[i][0] = 48*4+20;
				shopItemsButtons[i][1] = 48*3+40*i;
			}
			shopItemsButtons[stock.getStockSize()][0] = 48*4+20;
			shopItemsButtons[stock.getStockSize()][1] = 48*3+40*11;
		}
		
		brush.setFont(font.deriveFont(Font.PLAIN, 10F));
		brush.setColor(Color.white);
		int index = 0;
		Item item;
		try {
			item = stock.getItem(index);
			while (item != null) {
				brush.drawString("-"+item.getName()+" x"+stock.getAmount(index), 48*4+35, 48*3+40*index);
				index++;
				item = stock.getItem(index);
			}
		} catch (IndexOutOfRangeException e) {
			System.out.println(index);
			e.printStackTrace();
		}
		
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		
		brush.drawString("VOLTAR", shopItemsButtons[stock.getStockSize()][0]+15,
				   				   shopItemsButtons[stock.getStockSize()][1]);
		
		if (selectedButton > -1) {
			this.shopBuying();
		} else {
			displayArrow(this.shopItemsButtons, stock.getStockSize());
		}
		
	}
	
	private void shopChooseItemToSell(Inventory inventory, int selectedButton) {
		
		brush.setColor(Color.black);
		brush.fillRoundRect(48*4+10,48*2,48*5,48*11,10,10);
		
		if (this.shopItemsButtons == null) {
			shopItemsButtons = new int[inventory.getItemQuantity()+1][2];
			for (int i = 0; i < inventory.getItemQuantity(); i++) {
				shopItemsButtons[i][0] = 48*4+20;
				shopItemsButtons[i][1] = 48*3+40*i;
			}
			shopItemsButtons[inventory.getItemQuantity()][0] = 48*4+20;
			shopItemsButtons[inventory.getItemQuantity()][1] = 48*3+40*11;
		}
		
		brush.setFont(font.deriveFont(Font.PLAIN, 10F));
		brush.setColor(Color.white);
		Item item;
		try {
			for (int i = 0; i < 10; i++) {
				item = inventory.getItem(i);
				if (item == null) break;
				brush.drawString("-"+item.getName(), 48*4+35, 48*3+40*i);
			}
			
		} catch (IndexOutOfRangeException e) {
			e.printStackTrace();
		}
		
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		
		brush.drawString("VOLTAR", shopItemsButtons[inventory.getItemQuantity()][0]+15,
								   shopItemsButtons[inventory.getItemQuantity()][1]);
		
		
		if (selectedButton > -1) {
			this.shopSelling();
		} else {
			displayArrow(this.shopItemsButtons, inventory.getItemQuantity());
		}
		
	}
	
	private void shopBuying() {
		brush.setColor(Color.black);
		brush.fillRoundRect(48*9+20, 48*2+229,219, 48*11-229, 10,10);
		brush.setColor(Color.white);
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		brush.drawString("COMPRAR",this.productButtons[0][0]+15,this.productButtons[0][1]);
		brush.drawString("VOLTAR",this.productButtons[1][0]+15,this.productButtons[1][1]);
		
		displayArrow(this.productButtons, 1);
	}
	
	private void shopSelling() {
		brush.setColor(Color.black);
		brush.fillRoundRect(48*9+20, 48*2+229,219, 48*11-229, 10,10);
		brush.setColor(Color.white);
		brush.setFont(font.deriveFont(Font.PLAIN, 16F));
		brush.drawString("VENDER",this.productButtons[0][0]+15,this.productButtons[0][1]);
		brush.drawString("VOLTAR",this.productButtons[1][0]+15,this.productButtons[1][1]);
		
		displayArrow(this.productButtons, 1);
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