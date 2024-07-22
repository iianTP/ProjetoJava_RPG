package main.screen;

import javax.swing.JPanel;

import entities.enemies.*;
import entities.npcs.*;
import entities.npcs.bosses.*;
import entities.npcs.followers.*;
import entities.npcs.inanimates.*;
import entities.npcs.questNpcs.*;
import entities.npcs.sellers.*;
import entities.player.*;
import entities.teammates.*;
import exceptions.InvalidStatsInputException;
import states.*;

import main.KeyInput;
import main.Music;
import tiles.TheVoid;
import tiles.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class GameScreen extends JPanel {

	private final int tileSide = 48; // Texturas 48px x 48px
	private final int screenSide = 720; // Dimensões da tela 720px x 720px
	
	private long startNanoTime;
	private final double oneFrameInNano = 1000000000 / 60;
	
	private final KeyInput key = new KeyInput(this);
	
	private TileManager tiles;
	
	private UI ui;
	
	private TheVoid theVoid = new TheVoid();
	
	private Music music = new Music();
	
	private Player player;
	private Npc[] npcs;
	private Teammate[] teammates = new Teammate[3];
	private Enemie enemie;
	private Seller seller;
	
	private final int menu = 0;
	private final int playing = 1;
	private final int pause = 2;
	private final int talking = 3;
	private final int inventory = 4;
	private final int combat = 5;
	private final int buying = 6;
	private final int introduction = 7;
	private final int theEnd = 8;
	private final int dead = 9;

	private int gameState = 0;
	
	private String[] npcDialogue;
	
	private Dialogue dialogue;
	private Battle battle;
	private PlayerMenu playerMenu;
	private Shop shop;
	private MainMenu mainMenu;
	private Intro intro;
	private Ending ending;
	
	private TextAnimation ta = new TextAnimation();
	
	private Random random = new Random();
	
	private int battleCooldown = 300;
	private int deadTimer = 0;

	public GameScreen() {

		this.setPreferredSize(new Dimension(this.screenSide, this.screenSide));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
		
		this.ui = new UI(this.key, this.ta);
		
	}

	public void startGame(String choosenClass) {
		this.mainMenu = null;
		this.ta.resetTextAnimation();
		this.tiles = new TileManager();
		this.setPlayerClass(choosenClass);
		this.player.getCollision().setTiles(this.tiles);
		this.setNpcs();
		this.player.setNpcs(this.npcs);
	}
	
	private void restartGame() {
		this.ta.resetTextAnimation();
		this.player = null;
		this.npcs = null;
		this.tiles = null;
		this.gameState = this.menu;
	}
	
	// Identificação da classe escolhida pelo Player
	private void setPlayerClass(String choosenClass){
		
		String playerClass = choosenClass;

		if (playerClass.equals("mage")) {
			
			this.player = new Mage(key, this);
			
			this.teammates[0] = new AssassinNpc(this.player.getX()-24, this.player.getY()-48, this);
			this.teammates[1] = new HealerNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			this.teammates[2] = new WarriorNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);
			
		} else if (playerClass.equals("warrior")) {

			this.player = new Warrior(key, this);
			
			this.teammates[0] = new AssassinNpc(this.player.getX()-24, this.player.getY()-48, this);
			this.teammates[1] = new HealerNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			this.teammates[2] = new MageNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);

		} else if (playerClass.equals("healer")) {

			this.player = new Healer(key, this);
			
			this.teammates[0] = new AssassinNpc(this.player.getX()-24, this.player.getY()-48, this);
			this.teammates[1] = new MageNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			this.teammates[2] = new WarriorNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);

		} else if (playerClass.equals("assassin")) {

			this.player = new Assassin(key, this);
			
			this.teammates[0] = new MageNpc(this.player.getX()-24, this.player.getY()-48, this);
			this.teammates[1] = new HealerNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			this.teammates[2] = new WarriorNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);

		}
		
	}
	//
	
	private void setNpcs() {
		
		this.npcs = new Npc[43];
		
		this.npcs[0] = new LobbySeller(32*48,32*48,this);
		
		this.npcs[1] = new Door(this, 24+11*48, 24+28*48, "lobby", 41*48,49*48, "world1");
		this.npcs[2] = new Door(this, 24+26*48, 24+22*48, "lobby", 32*48,44*48, "world2");
		this.npcs[3] = new Door(this, 24+38*48, 24+22*48, "lobby", 48*48,41*48, "world3");
		this.npcs[4] = new Door(this, 24+51*48, 24+30*48, "lobby", 17*48,80*48, "world4");
		
		this.npcs[5] = new Door(this, 24+41*48, 24+50*48, "world1", 11*48, 27*48, "lobby");
		this.npcs[6] = new Door(this, 24+32*48, 24+45*48, "world2", 26*48, 21*48, "lobby");
		this.npcs[7] = new Door(this, 24+48*48, 24+42*48, "world3", 38*48, 21*48, "lobby");
		this.npcs[8] = new Door(this, 24+17*48, 24+81*48, "world4", 51*48, 29*48, "");
		
		this.npcs[9] = new Castle(this, "world1","castle1", 22*48, 11*48);
		this.npcs[10] = new Castle(this, "world2","castle2", 32*48, 12*48);
		this.npcs[11] = new Castle(this, "world3","castle3", 19*48, 18*48);
		
		this.npcs[12] = new Boss1Npc(this);
		this.npcs[13] = new Boss2Npc(this);
		this.npcs[14] = new Boss3Npc(this);
		
		this.npcs[15] = new House(this, 41*48, 18*48, "world1");
		this.npcs[16] = new House(this, 37*48, 19*48, "world1");
		this.npcs[17] = new House(this, 32*48, 15*48, "world1");
		
		this.npcs[18] = new House(this, 22*48, 30*48, "world2");
		this.npcs[19] = new House(this, 42*48, 30*48, "world2");
		
		this.npcs[20] = new House(this, 36*48, 27*48, "world3");
		this.npcs[21] = new House(this, 40*48, 18*48, "world3");
		
		this.npcs[22] = new CastleGate(this, "castle1", "world1", 22*48+24, 12*48+24);
		this.npcs[23] = new CastleGate(this, "castle2", "world2", 32*48+24, 13*48+24);
		this.npcs[24] = new CastleGate(this, "castle3", "world3", 19*48+24, 19*48+24);
		
		this.npcs[25] = new SentinelNpc(this, 21*48, 12*48-24);
		this.npcs[26] = new SentinelNpc(this, 23*48, 12*48-24);
		
		this.npcs[27] = new Fly(this);
		
		this.npcs[28] = new World4Mage(this, 30*48, 41*48, "right");
		this.npcs[29] = new World4Mage(this, 31*48, 40*48, "down");
		this.npcs[30] = new World4Mage(this, 32*48, 41*48, "left");
		
		this.npcs[31] = new FirePerson(this);
		
		this.npcs[32] = new PotionSeller(this);
		this.npcs[33] = new BookSeller(this);
		
		this.npcs[34] = new Glitch(this, this.player, this.npcs);
		this.npcs[34].collision().setTiles(this.tiles);
		
		this.npcs[35] = new Blob(this);
		
		this.npcs[36] = new Block(this);
		
		this.npcs[37] = new EntSeller(this);
		
		this.npcs[38] = new Bush(this);
		
		this.npcs[39] = new Shroom(this);
		
		this.npcs[40] = new Dancer(this);
		
		this.npcs[41] = new CultMember(this,5*48,4*48);
		this.npcs[42] = new CultMember(this,9*48,4*48);
		
	}

	public void runGameLoop() {

		// GAME LOOP
		while (true) {

			this.startNanoTime = System.nanoTime();

			update();
			repaint();

			// Loop a 60 FPS
			while (System.nanoTime() - this.startNanoTime < this.oneFrameInNano) {}

		}
		//

	}
	
	//atualização de estado do jogo
	private void update() {

		if (this.gameState == this.playing) {
			
			if (this.enemie != null) {
				this.enemie = null;
			}
			
			for (int i = 0; i < this.npcs.length; i++) {
				this.npcs[i].action();
			}
			
			this.player.update();
			searchBattle();
			
		}
		
		else if (this.gameState == this.menu) {
			if (this.mainMenu == null) {
				this.mainMenu = new MainMenu(this.key,this);
				this.music.playMusic("MainMenu");
			}
			this.mainMenu.mainMenu();
			this.key.setButtonCols(1);
		}
		
		else if (this.gameState == this.introduction) {
			
			if (this.intro == null) {
				this.intro = new Intro(this.key);
				this.music.stopMusic();
				this.music.playMusic("intro");
			}
			
			this.ta.checkStateChange(this.intro.isStateChanged(), -1);
			this.intro.intro();
			
			if (this.intro.isEnded()) {
				this.ta.resetTextAnimation();
				this.music.stopMusic();
				this.intro = null;
				this.gameState = playing;
			}
			
		}
		
		else if (this.gameState == this.theEnd) {
			
			if (this.ending == null) {
				this.ending = new Ending(this.key);
				this.music.stopMusic();
				this.music.playMusic("intro");
			}
			
			this.ta.checkStateChange(this.ending.isStateChanged(), -1);
			this.ending.ending();
			
			if (this.ending.isEnded()) {
				this.ta.resetTextAnimation();
				this.ending = null;
				this.music.stopMusic();
				this.restartGame();
			}
			
		}
		
		else if (gameState == talking) {
			if (this.npcDialogue == null) {
				this.npcDialogue = this.player.getCollision().getNpcNearby().getDialogue();
				this.dialogue = new Dialogue(this.key, this.npcDialogue);
				
				if (this.player.getGameStage() == 0) {
					this.music.playMusic("lobby");
				}
			}
			
			this.ta.checkStateChange(this.dialogue.isStateChanged(), -1);
			this.dialogue.dialogue();
			
			if (this.dialogue.isEnded()) {
				this.ta.resetTextAnimation();
				this.dialogue = null;
				this.npcDialogue = null;
				
				if (this.player.getGameStage() == 5) {
					this.gameState = this.theEnd;
				} else {
					this.gameState = this.playing;
				}
			}
		}
		
		else if (gameState == combat) {
			
			if (this.enemie == null) {
				this.enemie = new Ghost(this);
				this.battle = new Battle(this.player, this.enemie, this.key, this.teammates);
				this.music.stopMusic();
				this.music.playMusic("battle");
				this.key.setButtonCols(2);
			}
			
			this.ta.checkStateChange(this.battle.isStateChanged(), this.key.getCmdNum());
			this.battle.combat();
			
			if (battle.isEnded()) {
				this.ta.resetTextAnimation();
				this.gameState =this. playing;
				this.player.getQuestList().checkKillEnemiesQuests(this.enemie, this.battle.getWinner());
				
				if (this.battle.getWinner().equals("enemie")) {
					this.gameState = this.dead;
				}
				
				this.player.getSpells().resetDarkMagic();
				this.vanishBoss();
				this.enemie = null;
				this.battle = null;
				this.key.resetCmdNum();
			}
			
		} else if (this.gameState == this.dead) {

			this.deadTimer++;
			this.music.stopMusic();
			if (this.deadTimer == 300) {
				this.deadTimer = 0;
				this.restartGame();
				this.gameState = this.menu;
			}
			
		}
		
		else if (this.gameState == this.inventory) {
			
			if (this.playerMenu == null) {
				this.playerMenu = new PlayerMenu(this.key, this.player, this.teammates);
				this.key.setButtonCols(1);
			}
			
			this.ta.checkStateChange(this.playerMenu.isStateChanged(), -1);
			this.playerMenu.playerMenu();
			
			if (this.playerMenu.isEnded()) {
				this.playerMenu = null;
				this.gameState = this.playing;
			}
			
		} else if (this.gameState == this.buying) {
			
			if (this.shop == null) {
				
				if (this.player.getCollision().getNpcNearby() instanceof Seller) {
					this.seller = (Seller) this.player.getCollision().getNpcNearby();
					this.shop = new Shop(key, this.player, this.seller);
				}
				this.key.setButtonCols(1);
			}
			
			if (this.seller.isOutOfStock() || this.shop.isEnded()) {
				this.shop = null;
				this.seller = null;
				gameState = playing;
			} else {
				this.shop.shopCommands();
			}
			
		}

	}
	//
	
	private void searchBattle() {
		
		if (this.battleCooldown > 0) {
			this.battleCooldown--;
		}
		
		if (this.battleCooldown == 0 && !this.key.notWalking() && this.random.nextInt(150) == 0) {
			
			this.battleCooldown = 300;
			
			if (this.player.getLocation().equals("castle1")) {
				
				if (this.random.nextInt(2) == 0) {
					this.startBattle(new Sentinel(this));
				} else {
					this.startBattle(new Thief(this));
				}
				
			} else if (this.player.getLocation().equals("castle2")) {
				
				if (this.random.nextInt(2) == 0) {
					this.startBattle(new Slime(this));
				} else {
					this.startBattle(new Hedron(this));
				}
				
			} else if (this.player.getLocation().equals("castle3")) {
				
				this.startBattle(new Wizard(this));
				
			} else if (this.player.getLocation().equals("world4")) {
				
				this.startBattle(new Ghost(this));
				
			}
			
		}
		
	}
	
	public void startBattle(Enemie enemie) {
		this.enemie = enemie;
		this.gameState = this.combat;
		this.battle = new Battle(player, this.enemie, key, teammates);
	}
	
	private void vanishBoss() {
		
		if (this.enemie instanceof Boss1) {
			for (int i = 0; i < npcs.length; i++) {
				if (this.npcs[i] instanceof Boss1Npc) {
					((Boss1Npc) this.npcs[i]).vanish();
					break;
				}
			}
		}
		if (this.enemie instanceof Boss2) {
			for (int i = 0; i < npcs.length; i++) {
				if (this.npcs[i] instanceof Boss2Npc) {
					((Boss2Npc) this.npcs[i]).vanish();
					break;
				}
			}
		}
		if (this.enemie instanceof Boss3) {
			for (int i = 0; i < npcs.length; i++) {
				if (this.npcs[i] instanceof Boss3Npc) {
					((Boss3Npc) this.npcs[i]).vanish();
					break;
				}
			}
		}
		this.player.addGameStage();
	}

	
	//exibição gráfica do jogo
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		
		this.ui.setBrush(g2D);
		
		if (gameState == menu && this.mainMenu != null) {
			this.ui.mainScreen(this.mainMenu);
		}
		
		if (this.gameState == this.introduction && this.intro != null) {
			this.ui.intro(intro);
		}
		
		if (this.gameState == this.theEnd && this.ending != null) {
			this.ui.ending(this.ending);
		}
		
		if (this.gameState == this.dead) {
			this.ui.deadScreen();
		}
		
		if (this.player != null && this.npcs != null &&
			this.gameState != this.introduction &&
			this.gameState != this.theEnd && this.gameState != this.dead) {
			
			this.theVoid.draw(g2D);
			
			if (gameState == combat && this.enemie != null && this.battle != null) {
				this.ui.battleScreen(this.player, this.teammates,this.enemie, this.battle);
			} 
			
			if (gameState != combat && gameState != menu) {
				
				this.tiles.draw(g2D, this.player.getX(), this.player.getY());
				displayEnts(g2D);
				this.ui.draw(this.player.getX(), this.player.getY());
				
			}
			
			if (gameState == pause) {
				this.ui.pauseScreen();
			}
			
			if (gameState == inventory) {
				this.ui.playerMenu(this.playerMenu, this.player, this.teammates);
			}

			if (gameState == talking && this.dialogue != null) {
				this.ui.dialogue(this.dialogue);
			}
			
			if (gameState == buying && this.seller != null && this.shop != null) {
				this.ui.shopScreen(seller.getStock(), this.shop, this.player);
			}
			
		}

		g2D.dispose();

	}
	//
	
	//exibição das entidades
	private void displayEnts(Graphics2D g2D) {
		
		Npc[] npcsBehind = new Npc[0];
		Npc[] npcsInFront = new Npc[0];

		for (int i = 0; i < npcs.length; i++) {

			if (this.player.getScreenY() >= npcs[i].getScreenY()) {
				
				npcsBehind = Arrays.copyOf(npcsBehind, npcsBehind.length+1);
				npcsBehind[npcsBehind.length-1] = npcs[i];
				
			} else {
				
				npcsInFront = Arrays.copyOf(npcsInFront, npcsInFront.length+1);
				npcsInFront[npcsInFront.length-1] = npcs[i];
				
			}

		}
		
		sortYCoords(npcsBehind);
		sortYCoords(npcsInFront);
		
		for (int i = 0; i < npcsBehind.length; i++) {
			npcsBehind[i].draw(g2D, this.player);
		}

		this.player.draw(g2D);

		for (int i = 0; i < npcsInFront.length; i++) {
			npcsInFront[i].draw(g2D, this.player);
		}
		
	}
	//
	
	//ordenação das entidades pela coordenada Y na tela
	private void sortYCoords(Npc[] npcs) {
		
		Npc t;
		for (int i = 0; i < npcs.length-1; i++) {
			for (int j = 0; j < npcs.length-1; j++) {
				if (npcs[j].getScreenY() > npcs[j+1].getScreenY()) {
					t = npcs[j];
					npcs[j] = npcs[j+1];
					npcs[j+1] = t;
				}
			}
		}
		
	}
	//

	public void changeMap(String map, int x, int y) {
		this.tiles.setCurrentMap(map);
		this.player.setLocation(map);
		
		if (!map.split(map.charAt(map.length()-1)+"")[0].equals("castle") && !map.equals("world4")) {
			this.music.stopMusic();
			this.music.playMusic(map);
		}
		
		if (map.equals("lobby") && this.random.nextInt(0,10) == 0) {
			this.npcs[8].setLocation("lobby");
		} else {
			this.npcs[8].setLocation("");
		}
		
		if (!map.equals("lobby") && !map.equals("world4") && this.random.nextInt(0,5) == 0) {
			((EntSeller) this.npcs[37]).appear(map);
		} else {
			this.npcs[37].setLocation("");
		}
		
		this.player.setX(x);
		this.player.setY(y);

	}
	
	public int getTileSide() {
		return this.tileSide;
	}
	public int getScreenSide() {
		return this.screenSide;
	}
	
	public int getGameState() {
		return this.gameState;
	}
	
	public void setIntroState() {
		this.gameState = this.introduction;
	}
	public void setPlayingState() {
		this.gameState = this.playing;
	}
	public void setInventoryState() {
		this.gameState = this.inventory;
	}
	public void setCombatState() {
		this.gameState = this.combat;
	}
	public void setShopState() {
		this.gameState = this.buying;
	}
	public void setPauseState() {
		this.gameState = this.pause;
	}
	public void setDialogueState() {
		this.gameState = this.talking;
	}

	public void setNpcDialogue(String[] npcDialogue) {
		this.npcDialogue = npcDialogue;
	}

	public Enemie getEnemie() {
		return this.enemie;
	}
	public void setEnemie(Enemie enemie) {
		this.enemie = enemie;
	}

}