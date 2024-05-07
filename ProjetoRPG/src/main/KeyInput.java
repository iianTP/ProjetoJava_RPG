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
		
		switch (key) {
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_E:
			interaction = true;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_E:
			interaction = false;
			break;
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
