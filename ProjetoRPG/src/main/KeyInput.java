package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private boolean up, down, left, right;
	private boolean interaction;
	private boolean pause;
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			this.up = true;
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			this.down = true;
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			this.left = true;
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			this.right = true;
		} else if (key == KeyEvent.VK_E) {
			this.interaction = true;
		} else if (key == KeyEvent.VK_P) {
			this.pause = pause ? false : true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			this.up = false;
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			this.down = false;
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			this.left = false;
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			this.right = false;
		} else if (key == KeyEvent.VK_E) {
			this.interaction = false;
		}
		
	}
	
	public boolean goingUp() {
		return this.up;
	}
	public boolean goingDown() {
		return this.down;
	}
	public boolean goingLeft() {
		return this.left;
	}
	public boolean goingRight() {
		return this.right;
	}
	
	public boolean notWalking() {
		return (!this.up && !this.down && !this.left && !this.right) ? true : false;
	}

	public boolean isInteracting() {
		return this.interaction;
	}
	
	public boolean isPaused() {
		return this.pause;
	}

}
