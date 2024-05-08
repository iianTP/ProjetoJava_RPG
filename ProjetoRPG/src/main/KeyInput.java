package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private boolean up, down, left, right;
	private boolean interaction;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			up = true;
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			down = true;
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			left = true;
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			right = true;
		} else if (key == KeyEvent.VK_E) {
			interaction = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			up = false;
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			down = false;
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			left = false;
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (key == KeyEvent.VK_E) {
			interaction = false;
		}
		
	}
	
	public boolean goingUp() {
		return up;
	}
	public boolean goingDown() {
		return down;
	}
	public boolean goingLeft() {
		return left;
	}
	public boolean goingRight() {
		return right;
	}
	
	public boolean notWalking() {
		
		if (!up && !down && !left && !right) {
			return true;
		} else {
			return false;
		}
		
	}

	public boolean isInteracting() {
		return interaction;
	}

}
