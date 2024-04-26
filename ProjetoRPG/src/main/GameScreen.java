package main;

import javax.swing.JPanel;

import Entities.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

public class GameScreen extends JPanel implements Runnable {
	
	private int textureResolution = 48; // Texturas 48px x 48px
	private int screenSide = 720; // Dimensões da tela 720px x 720px
	
	
	private long startNanoTime;
	private double oneFrameInNano = 1000000000/60;

	private KeyInput key = new KeyInput();
	
	private Thread gameThread;
	
	private Player player = new Player(key);
	
	public GameScreen() {
		
		this.setPreferredSize(new Dimension(this.screenSide, this.screenSide));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(key);
		this.setFocusable(true);
		
	}
	
	public void startThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		while(gameThread != null) {
			
			this.startNanoTime = System.nanoTime();
			
			player.update();
			repaint();
			
			while(System.nanoTime() - this.startNanoTime < this.oneFrameInNano) {
				continue;
			}
			
		}
		
	}
	
	public void update() {

	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		player.draw(g2D);

		g2D.dispose();
		
	}
	
}
