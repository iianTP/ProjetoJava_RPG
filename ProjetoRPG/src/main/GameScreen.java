package main;

import javax.swing.JPanel;

import classes.Assassin;
import classes.Healer;
import classes.Mage;
import classes.Warrior;
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

public class GameScreen extends JPanel implements Runnable {
	
	private ScreenInfo screen = new ScreenInfo();
	
	private long startNanoTime;
	private double oneFrameInNano = 1000000000/60;

	private TileManager tiles = new TileManager();
	private KeyInput key = new KeyInput(this);
	private Npc[] npcs = new Npc[2];
	private TheVoid theVoid = new TheVoid();
	private UI ui = new UI();
	
	private Thread gameThread;
	private Player player;
	
	private int gameState = 1;
	private final int menu = 0;
	private final int playing = 1;
	private final int pause = 2;
	private final int battle = 3;
	
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
			
			this.player = new Mage(key, npcs);
			
		} else if (playerClass.equals("warrior")) {
			
			this.player = new Warrior(key, npcs);
			
		} else if (playerClass.equals("healer")) {
			
			this.player = new Healer(key, npcs);
			
		} else if (playerClass.equals("assassin")) {
			
			this.player = new Assassin(key, npcs);
			
		}
		
		this.npcs[0] = new Test(1157,1157);
		this.npcs[1] = new Test(1000,1000);
			
		this.gameThread = new Thread(this);
		this.gameThread.start();
		
	}
	
	@Override
	public void run() {
		
		// GAME LOOP
		while(gameThread != null) {
			
			this.startNanoTime = System.nanoTime();
			
			update();
			repaint();
			
			// Loop a 60 FPS
			while(System.nanoTime() - this.startNanoTime < this.oneFrameInNano) {
				continue;
			}
			
		}
		
	}
	
	public void update() {
		
		if (gameState == playing) {
			this.player.update();
			this.npcs[0].update(this.player);
		}
		
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		
		this.theVoid.draw(g2D);

		
		if (this.player != null && this.npcs != null) {
			
			this.tiles.draw(g2D, this.player.getX(), this.player.getY());
					
			// Sistema de profundidade entre NPC e Player 
			int count = 0;
			int[] npcInFrontIndex = new int[0];
			
			for (int i = 0; i < npcs.length; i++) {
				
				if (this.screen.screenSide()/2 - 24 >= this.npcs[i].getY() - this.player.getY() + this.screen.screenSide()/2) {
					
					this.npcs[i].draw(g2D, this.player.getX(), this.player.getY());
				
				} else {
					
					npcInFrontIndex = Arrays.copyOf(npcInFrontIndex, count+1);
					npcInFrontIndex[count] = i;
					count++;
					
				}
				
			}
			
			this.player.draw(g2D, gameState);
			
			for (int i = 0; i < npcInFrontIndex.length; i++) {
				npcs[npcInFrontIndex[i]].draw(g2D, this.player.getX(), this.player.getY());
			}
			//
			
		}
		
		if (gameState == pause) {
			this.ui.pauseScreen(g2D);
		}
		
		this.ui.draw(g2D);
	
		g2D.dispose();
		
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	
	public void changeMap(String map) {
		this.tiles = new TileManager(/*map*/);
		this.player.setX(894);
		this.player.setY(894);
		
	}
	
}
