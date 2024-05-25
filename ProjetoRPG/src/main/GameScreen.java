package main;

import javax.swing.JPanel;

import classes.Assassin;
import classes.Healer;
import classes.Mage;
import classes.Warrior;
import entities.Entity;
import entities.Npc;
import entities.Player;
import npcs.Test;
import tiles.TheVoid;
import tiles.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GameScreen extends JPanel implements Runnable {

	private ScreenInfo screen = new ScreenInfo();

	private long startNanoTime;
	private final double oneFrameInNano = 1000000000 / 60;

	private TileManager tiles = new TileManager();
	private KeyInput key = new KeyInput(this);
	private Npc[] npcs = new Npc[2];
	private TheVoid theVoid = new TheVoid();
	private UI ui = new UI();

	private Thread gameThread;
	private Player player;
	
	private final int menu = 0;
	private final int playing = 1;
	private final int pause = 2;
	private final int dialogue = 3;
	private final int inventory = 4;
	private final int battle = 5;

	private int gameState = playing;
	
	private String[] npcDialogue;

	public GameScreen() {

		this.setPreferredSize(new Dimension(screen.screenSide(), screen.screenSide()));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);

	}

	public void startThread() {

		String playerClass = "mage";

		// Identificação da classe escolhida
		if (playerClass.equals("mage")) {

			this.player = new Mage(key, npcs, this);

		} else if (playerClass.equals("warrior")) {

			this.player = new Warrior(key, npcs, this);

		} else if (playerClass.equals("healer")) {

			this.player = new Healer(key, npcs, this);

		} else if (playerClass.equals("assassin")) {

			this.player = new Assassin(key, npcs, this);

		}
//1157
		this.npcs[0] = new Test(1048, 1012, this);
		this.npcs[1] = new Test(1000, 1000, this);

		this.gameThread = new Thread(this);
		this.gameThread.start();

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

	}

	public void update() {

		if (gameState == playing) {
			
			this.player.update();
			
			for (int i = 0; i < npcs.length; i++) {
				this.npcs[i].update(this.player, this.npcs);
			}
			
		}

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;

		this.theVoid.draw(g2D);

		if (this.player != null && this.npcs != null) {
			
			if (gameState == battle) {
				
				try {
					this.ui.battleScreen(g2D);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				
				this.tiles.draw(g2D, this.player.getX(), this.player.getY());

				displayEnts(g2D);
				
				this.ui.draw(g2D, this.player.getX(), this.player.getY());
				
			}
			
			if (gameState == pause) {
				this.ui.pauseScreen(g2D);
			}

			if (gameState == dialogue) {
				
				int index = this.key.getDialogueIndex();
				if (index == this.npcDialogue.length) {
					this.key.resetDialogueIndex();
					this.gameState = playing;
				} else {
					this.ui.dialogueBox(g2D);
					this.ui.dialogueText(g2D, npcDialogue[index]);
				}

			}
			
		}

		g2D.dispose();

	}
	
	public void displayEnts(Graphics2D g2D) {
		
		Npc[] npcsBehind = new Npc[0];
		Npc[] npcsInFront = new Npc[0];

		for (int i = 0; i < npcs.length; i++) {

			if (this.player.getScreenY() >= this.npcs[i].getScreenY()) {
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

		this.player.draw(g2D, gameState);

		for (int i = 0; i < npcsInFront.length; i++) {
			npcsInFront[i].draw(g2D, this.player.getX(), this.player.getY());
		}
		
	}
	
	public void sortYCoords(Npc[] a) {
		
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

	public int getGameState() {
		return this.gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public void changeMap(String map) {
		this.tiles = new TileManager(/* map */);
		this.player.setX(894);
		this.player.setY(894);

	}

	public void setNpcDialogue(String[] npcDialogue) {
		this.npcDialogue = npcDialogue;
	}

}
