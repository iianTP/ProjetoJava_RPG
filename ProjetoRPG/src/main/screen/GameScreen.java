package main.screen;

import javax.swing.JPanel;

import entities.enemies.*;
import entities.npcs.*;
import entities.npcs.teammates.*;
import entities.player.*;
import states.*;

import main.KeyInput;

import tiles.TheVoid;
import tiles.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Arrays;

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
	private Npc[] npcs = new Npc[2];
	private Teammate[] teammates = new Teammate[3];
	private Npc[] allNpcs = new Npc[npcs.length+teammates.length];
	private Enemie enemie;
	private Seller seller;
	//private Entity[] team = new Entity[3];
	
	private final int menu = 0;
	private final int playing = 1;
	private final int pause = 2;
	private final int talking = 3;
	private final int inventory = 4;
	private final int combat = 5;
	private final int buying = 6;

	private int gameState = playing;
	
	private String[] npcDialogue;
	
	private Dialogue dialogue;
	private Battle battle;
	private PlayerMenu playerMenu; //= new PlayerMenu(this.key, this.player, this.teammates);
	private Shop shop;

	public GameScreen() {

		this.setPreferredSize(new Dimension(this.screenSide, this.screenSide));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
		
		this.ui = new UI(this.key);
		
	}

	//inicialização do game loop
	public void startThread() {
		
		
		setPlayerClass();
		
		setAllNpcs();
		
		this.player.setNpcs(allNpcs);
		
		this.gameThread = new Thread(this);
		this.gameThread.start();

	}
	//
	
	// Identificação da classe escolhida pelo Player
	private void setPlayerClass(){
		
		String playerClass = "mage";

		if (playerClass.equals("mage")) {
			
			this.player = new Mage(key, this);
			
			teammates[0] = new AssassinNpc(this.player.getX()-24, this.player.getY()-48, this);
			teammates[1] = new HealerNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			teammates[2] = new WarriorNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);
			
		} else if (playerClass.equals("warrior")) {

			this.player = new Warrior(key, this);
			
			teammates[0] = new AssassinNpc(this.player.getX()-24, this.player.getY()-48, this);
			teammates[1] = new HealerNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			teammates[2] = new MageNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);

		} else if (playerClass.equals("healer")) {

			this.player = new Healer(key, this);
			
			teammates[0] = new AssassinNpc(this.player.getX()-24, this.player.getY()-48, this);
			teammates[1] = new MageNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			teammates[2] = new WarriorNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);

		} else if (playerClass.equals("assassin")) {

			this.player = new Assassin(key, this);
			
			teammates[0] = new MageNpc(this.player.getX()-24, this.player.getY()-48, this);
			teammates[1] = new HealerNpc(this.player.getX()-24, this.player.getY()-48-24, this);
			teammates[2] = new WarriorNpc(this.player.getX()-24, this.player.getY()-48-24-24, this);

		}
		
	}
	//
	
	private void setAllNpcs() {
		this.npcs[0] = new Test(1333, 1386, this);
		this.npcs[1] = new Seller(1224, 1234, this);
		
		System.arraycopy(npcs, 0, allNpcs, 0, npcs.length);
		System.arraycopy(teammates, 0, allNpcs, npcs.length, 3);
	}

	@Override
	public void run() {

		// GAME LOOP
		while (gameThread != null) {

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
			
			for (int i = 0; i < allNpcs.length; i++) {
				//this.allNpcs[i].update(this.player, this.allNpcs);
			}
			
		} else if (gameState == talking) {
			if (this.npcDialogue == null) {
				this.npcDialogue = this.player.getCollision().getNpcNearby().getDialogue();
				this.dialogue = new Dialogue(this.key, this.npcDialogue.length);
			}
			
			this.dialogue.dialogue();
			
			if (this.dialogue.isDialogueEnded()) {
				this.dialogue = null;
				this.npcDialogue = null;
				gameState = playing;
			}
		}
		
		else if (gameState == combat) {
			
			if (this.enemie == null) {
				this.enemie = new Ghost();
				this.battle = new Battle(player, enemie, key, teammates);
				this.key.setButtonCols(2);
			}
			
			this.battle.combat();
			
			if (battle.isBattleEnded()) {
				gameState = playing;
				this.enemie = null;
				this.battle = null;
			}
			
		} else if (gameState == inventory) {
			
			if (this.playerMenu == null) {
				playerMenu = new PlayerMenu(this.key, this.player, this.teammates);
			}
			
			this.key.setButtonCols(1);
			this.playerMenu.playerMenu();
			
		} else if (gameState == buying) {
			
			if (this.shop == null) {
				
				if (this.player.getCollision().getNpcNearby() instanceof Seller) {
					this.seller = (Seller) this.player.getCollision().getNpcNearby();
					this.shop = new Shop(key, this.player, this.seller);
				}
				this.key.setButtonCols(1);
			}
			
			if (this.seller.isOutOfStock()) {
				this.shop = null;
				this.seller = null;
				gameState = playing;
			} else {
				this.shop.shopCommands();
			}
			
			
		}

	}
	//

	
	//exibição gráfica do jogo
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		
		this.ui.setBrush(g2D);
		
		this.theVoid.draw(g2D);

		if (this.player != null && this.npcs != null) {
			
			if (gameState == combat && this.enemie != null) {

				this.ui.battleScreen(this.player, this.enemie, this.battle);
				
				this.ui.battleText(this.battle.getMessage());
				
				g2D.drawImage(this.player.getIdleSprites()[0], 96*3, 128*3, 48, 48, null);
				
				g2D.drawImage(this.enemie.getSprite(), 104*3, 40*3, 48*2, 48*2, null);
				
			} else {
				
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
				
			/*	int index = this.key.getDialogueIndex();
				if (index == this.npcDialogue.length) {
					this.key.resetDialogueIndex();
					this.gameState = playing;
				} else {*/
					this.ui.dialogueBox();
					this.ui.dialogueText(npcDialogue[this.dialogue.getDialogueIndex()]);
				/*}*/

			}
			
			if (gameState == buying && this.seller != null) {
				this.ui.shopScreen(seller.getStock(), this.shop, this.player.getGold());
			}
			
		}

		g2D.dispose();

	}
	//
	
	//exibição das entidades
	private void displayEnts(Graphics2D g2D) {
		
		Npc[] npcsBehind = new Npc[0];
		Npc[] npcsInFront = new Npc[0];

		for (int i = 0; i < allNpcs.length; i++) {

			if (this.player.getScreenY() >= allNpcs[i].getScreenY()) {
				
				npcsBehind = Arrays.copyOf(npcsBehind, npcsBehind.length+1);
				npcsBehind[npcsBehind.length-1] = allNpcs[i];
				
			} else {
				
				npcsInFront = Arrays.copyOf(npcsInFront, npcsInFront.length+1);
				npcsInFront[npcsInFront.length-1] = allNpcs[i];
				
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
	private void sortYCoords(Npc[] a) {
		
		Npc t;
		for (int i = 0; i < a.length-1; i++) {
			for (int j = 0; j < a.length-1; j++) {
				if (a[j].getScreenY() > a[j+1].getScreenY()) {
					t = a[j];
					a[j] = a[j+1];
					a[j+1] = t;
				}
			}
		}
		
	}
	//

	
	


	public void changeMap(String map) {
		this.tiles = new TileManager(/* map */);
		this.player.setX(894);
		this.player.setY(894);

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
	public void setGameState(int gameState) {
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