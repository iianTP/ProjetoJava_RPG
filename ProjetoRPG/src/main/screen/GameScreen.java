package main.screen;

import javax.swing.JPanel;

import entities.enemies.*;
import entities.npcs.*;
import entities.npcs.questNpc.QTest;
import entities.player.*;
import entities.teammates.*;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidGameStateIndex;
import states.*;

import main.KeyInput;

import tiles.TheVoid;
import tiles.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class GameScreen extends JPanel implements Runnable {

	private final int tileSide = 48; // Texturas 48px x 48px
	private final int screenSide = 720; // Dimensões da tela 720px x 720px
	
	private long startNanoTime;
	private final double oneFrameInNano = 1000000000 / 60;
	
	private Thread gameThread;

	private final KeyInput key = new KeyInput(this);
	
	private TileManager tiles = new TileManager();
	
	private UI ui;
	
	private TheVoid theVoid = new TheVoid();
	
	private Player player;
	private Npc[] npcs = new Npc[3];
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

	private int gameState = 0;
	
	private String[] npcDialogue;
	
	private Dialogue dialogue;
	private Battle battle;
	private PlayerMenu playerMenu; //= new PlayerMenu(this.key, this.player, this.teammates);
	private Shop shop;
	private MainMenu mainMenu;
	private TextAnimation ta = new TextAnimation();

	public GameScreen() {

		this.setPreferredSize(new Dimension(this.screenSide, this.screenSide));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
		
		this.ui = new UI(this.key, this.ta);
		
	}

	//inicialização do game loop
	public void startThread() {
		
		this.gameThread = new Thread(this);
		this.gameThread.start();

	}
	//
	
	public void startGame(String choosenClass) {
		this.setPlayerClass(choosenClass);
		this.setNpcs();
		this.player.setNpcs(npcs);
		this.player.getCollision().setTiles(tiles);
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
		this.npcs[0] = new Test(1333, 1386, this);
		this.npcs[1] = new Seller(1224, 1234, this);
		this.npcs[2] = new QTest(900,900,this);
	}

	@Override
	public void run() {

		// GAME LOOP
		while (this.gameThread != null) {

			this.startNanoTime = System.nanoTime();

			update();
			repaint();

			// Loop a 60 FPS
			while (System.nanoTime() - this.startNanoTime < this.oneFrameInNano) {}

		}
		//

	}
	
	//atualização das entidades
	private void update() {

		if (gameState == playing) {
			
			if (this.enemie != null) {
				this.enemie = null;
			}
			
			this.player.update();
			startBattle();
			
			for (int i = 0; i < npcs.length; i++) {
				//this.allNpcs[i].update(this.player, this.allNpcs);
			}
			
		}
		
		else if (gameState == menu) {
			if (this.mainMenu == null) {
				this.mainMenu = new MainMenu(this.key,this);
			}
			this.mainMenu.mainMenu();
			this.key.setButtonCols(1);
		}
		
		else if (gameState == talking) {
			if (this.npcDialogue == null) {
				this.npcDialogue = this.player.getCollision().getNpcNearby().getDialogue();
				this.dialogue = new Dialogue(this.key, this.npcDialogue);
			}
			
			this.ta.checkStateChange(this.dialogue.isDialogueChanged(), -1);
			this.dialogue.dialogue();
			
			if (this.dialogue.isDialogueEnded()) {
				this.ta.resetTextAnimation();
				this.dialogue = null;
				this.npcDialogue = null;
				gameState = playing;
			}
		}
		
		else if (gameState == combat) {
			
			if (this.enemie == null) {
				this.enemie = new Ghost(this);
				this.battle = new Battle(player, enemie, key, teammates);
				this.key.setButtonCols(2);
			}
			
			this.ta.checkStateChange(this.battle.isChangedBattleState(), this.key.getCmdNum());
			this.battle.combat();
			
			if (battle.isBattleEnded()) {
				this.ta.resetTextAnimation();
				gameState = playing;
				this.player.getQuestList().checkKillEnemiesQuests(enemie, this.battle.getWinner());
				this.player.getSpells().resetDarkMagic();
				this.enemie = null;
				this.battle = null;
				this.key.resetCmdNum();
			}
			
		} else if (gameState == inventory) {
			
			if (this.playerMenu == null) {
				playerMenu = new PlayerMenu(this.key, this.player, this.teammates);
			}
			
			this.key.setButtonCols(1);
			this.playerMenu.playerMenu();
			
			if (this.playerMenu.isClosedMenu()) {
				this.playerMenu = null;
				this.gameState = this.playing;
			}
			
		} else if (gameState == buying) {
			
			if (this.shop == null) {
				
				if (this.player.getCollision().getNpcNearby() instanceof Seller) {
					this.seller = (Seller) this.player.getCollision().getNpcNearby();
					this.shop = new Shop(key, this.player, this.seller);
				}
				this.key.setButtonCols(1);
			}
			
			if (this.seller.isOutOfStock() || this.shop.isExitedShop()) {
				this.shop = null;
				this.seller = null;
				gameState = playing;
			} else {
				this.shop.shopCommands();
			}
			
			
		}

	}
	//
	
	private Random r = new Random();
	
	private void startBattle() {
		
		File[] fileList = new File("./src/entities/enemies").listFiles();
		
		if (!this.key.notWalking() && this.r.nextInt(100) == 0) {
			
			System.out.println(fileList[this.r.nextInt(fileList.length)].getName());
			
		}
		
	}

	
	//exibição gráfica do jogo
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		
		this.ui.setBrush(g2D);
		
		if (gameState == menu && this.mainMenu != null) {
			this.ui.mainScreen(this.mainMenu);
		}
		
		if (this.player != null && this.npcs != null) {
			
			this.theVoid.draw(g2D);
			
			if (gameState == combat && this.enemie != null) {
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
			npcsBehind[i].draw(g2D, this.player.getX(), this.player.getY());
		}

		this.player.draw(g2D);

		for (int i = 0; i < npcsInFront.length; i++) {
			npcsInFront[i].draw(g2D, this.player.getX(), this.player.getY());
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

	public void changeMap(String map) {
		this.tiles = new TileManager(/* map */);
		try {
			this.player.setX(894);
			this.player.setY(894);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}

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
	public void setGameState(int gameState) throws InvalidGameStateIndex {
		if (gameState < 0 || gameState > 6) {
			throw new InvalidGameStateIndex("estado de jogo "+gameState+" inválido");
		}
		this.gameState = gameState;
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