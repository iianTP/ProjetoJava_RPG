
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameScreen extends JPanel implements Runnable {
	
	private int textureResolution = 48; // Texturas 48px x 48px
	private int screenSide = 720; // Dimensões da tela 720px x 720px
	
	// Coordenadas do player
	private int playerX = 337;
	private int playerY = 337;
	
	private long startNanoTime;
	
	private double oneFrameInNano = 1000000000/60;

	KeyInput key = new KeyInput();
	
	Thread gameThread;
	
	public GameScreen() {
		
		this.setPreferredSize(new Dimension(screenSide, screenSide));
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
			
			update();
			repaint();
			
			while(System.nanoTime() - this.startNanoTime < this.oneFrameInNano) {
				continue;
			}
			
		}
		
	}
	
	public void update() {
		if (key.up) {
			playerY -= 5;
		} else if (key.down) {
			playerY += 5;
		} else if (key.left) {
			playerX -= 5;
		} else if (key.right) {
			playerX += 5;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(Color.white);
		
		g2D.fillRect(playerX, playerY, textureResolution,textureResolution);
		
		g2D.dispose();
		
	}
	
}
