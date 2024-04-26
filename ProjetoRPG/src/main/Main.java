package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		
		GameScreen screen = new GameScreen();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.setTitle("Projeto-RPG");
		window.add(screen);
		window.pack();
		window.setLocationRelativeTo(null);
		
		screen.startThread();
		
	}

}
