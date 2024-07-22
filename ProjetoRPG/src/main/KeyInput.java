package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.screen.GameScreen;

public class KeyInput implements KeyListener {

	private boolean up, down, left, right;
	private boolean interaction;
	
	private int cmdNum = 0;
	
	private int buttonCols;
	private int maxCmdNum;
	
	private GameScreen gs;
	
	public KeyInput(GameScreen gs) {
		this.gs = gs;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (gs.getGameState() == 0) {
			
			if (key == KeyEvent.VK_E) {
				this.interaction = true;
			}
			
			this.commandNum(key);
			
		} else if (gs.getGameState() == 1) {
			
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
				gs.setPauseState();
			} else if (key == KeyEvent.VK_Z) {
				gs.setInventoryState();
			}
			
		} else if (gs.getGameState() == 2) {
			
			if (key == KeyEvent.VK_P) {
				gs.setPlayingState();
			}
			
		} else if (gs.getGameState() == 3 || gs.getGameState() == 7) {
			
			if (key == KeyEvent.VK_E) {
				this.interaction = true;
			}
			
		} else if (gs.getGameState() == 4 || gs.getGameState() == 5 || gs.getGameState() == 6) {
			
			if (key == KeyEvent.VK_E) {
				this.interaction = true;
			}
			
			this.commandNum(key);
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
	
	// DIREÇÕES DE CAMINHADA
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
	//
	
	public boolean notWalking() {
		return !this.up && !this.down && !this.left && !this.right;
	}

	public boolean isInteracting() {
		if (this.interaction) {
			this.interaction = false;
			return true;
		} else {
			return false;
		}
	}

	public int getCmdNum() {
		return this.cmdNum;
	}
	public void resetCmdNum() {
		this.cmdNum = 0;
	}
	
	public void commandNum(int key) {
		
		int cmdNumBackup = this.cmdNum;
		
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			this.cmdNum -= this.buttonCols;
		} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
			this.cmdNum += this.buttonCols;
		} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			if (this.buttonCols > 1 && this.cmdNum % this.buttonCols != 0) {
				this.cmdNum--;
			}
		} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			if (this.buttonCols > 1 && (this.cmdNum+1) % this.buttonCols != 0) {
				this.cmdNum++;
			}
		}
		
		if (this.cmdNum < 0 || this.cmdNum > this.maxCmdNum) {
			this.cmdNum = cmdNumBackup;
		}
		
	}

	public void setButtonCols(int buttonCols) {
		this.buttonCols = buttonCols;
	}

	public void setMaxCmdNum(int maxCmdNum) {
		this.maxCmdNum = maxCmdNum;
	}
	
}