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

public class GameScreen extends JPanel implements Runnable {
	
	private ScreenInfo screen = new ScreenInfo();
	
	private long startNanoTime;
	private double oneFrameInNano = 1000000000/60;

	private TileManager tiles = new TileManager();
	private KeyInput key = new KeyInput();
	private Npc[] npcs = new Npc[1];
	private TheVoid theVoid = new TheVoid();
	private Thread gameThread;
	private Player player;
	
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
		
		this.npcs[0] = new Test();
			
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
		
			this.player.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		
		this.theVoid.draw(g2D);

		
		if (this.player != null && this.npcs != null) {
			
			this.tiles.draw(g2D, this.player.getX(), this.player.getY());
			
			if (this.screen.screenSide()/2 - 24 < this.npcs[0].getY() - this.player.getY() + this.screen.screenSide()/2) {
				
				this.player.draw(g2D);
			
				this.npcs[0].draw(g2D, this.player.getX(), this.player.getY());
			
			} else {
				
				this.npcs[0].draw(g2D, this.player.getX(), this.player.getY());
				
				this.player.draw(g2D);
			}
			
			
		}
	
		g2D.dispose();
		
	}
	
}
