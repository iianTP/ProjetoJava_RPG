package main.screen;

import javax.swing.JFrame;

public class FrameSetup extends JFrame {
	
	public FrameSetup() {

		GameScreen screen = new GameScreen();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Projeto-RPG");
		this.add(screen);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		screen.startThread();
		
	}
	
}
