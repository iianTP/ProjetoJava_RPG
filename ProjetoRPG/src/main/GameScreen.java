package main;

import javax.swing.JPanel;

import Player.Assassin;
import Player.Healer;
import Player.Mage;
import Player.Player;
import Player.Warrior;
import Tiles.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameScreen extends JPanel implements Runnable {
	
	private int tileResolution = 48; // Texturas 48px x 48px
	private int screenSide = 720; // Dimensões da tela 720px x 720px
	
	private long startNanoTime;
	private double oneFrameInNano = 1000000000/60;
	
	private int gameState = 0;

	private TileManager tiles = new TileManager();
	private KeyInput key = new KeyInput();
	private Thread gameThread;
	private Player player;
	
	public GameScreen() {
		
		this.setPreferredSize(new Dimension(this.screenSide, this.screenSide));
		this.setBackground(Color.gray);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
		
	}
	
	public void startThread() {
		
		String playerClass = "mage";
		
		// Identificação da classe escolhida
		if (playerClass.equals("mage")) {
			
			this.player = new Mage(key);
			
		} else if (playerClass.equals("warrior")) {
			
			this.player = new Warrior(key);
			
		} else if (playerClass.equals("healer")) {
			
			this.player = new Healer(key);
			
		} else if (playerClass.equals("assassin")) {
			
			this.player = new Assassin(key);
			
		}
			
		this.gameThread = new Thread(this);
		this.gameThread.start();
		
	}
	
	public int getTileResolution() {
		return tileResolution;
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
		
		tiles.draw(g2D);
		
		if (this.player != null) {
			this.player.draw(g2D);
		}
	
		g2D.dispose();
		
	}
	
}