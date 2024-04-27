package main;

import java.util.Scanner;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		
		GameScreen screen = new GameScreen();
		
		// TESTE DE CLASSE DE PERSONAGEM
		Scanner setClass = new Scanner(System.in);
		String classString = setClass.nextLine();
		//
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.setTitle("Projeto-RPG");
		window.add(screen);
		window.pack();
		window.setLocationRelativeTo(null);
		
		screen.startThread(classString);
		setClass.close();
	}

}
