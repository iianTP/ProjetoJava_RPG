package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.screen.GameScreen;

public class KeyInput implements KeyListener {

	private boolean up, down, left, right;
	private boolean interaction;
	
	private int dialogueIndex = 0;
	
	private int cmdNum = 0;
	
	private GameScreen gs;
	
	public KeyInput(GameScreen gs) {
		this.gs = gs;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if (gs.getGameState() == 1) {
			
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
				gs.setGameState(2);
			} else if (key == KeyEvent.VK_B) {
				gs.setGameState(5);
			}
			
		} else if (gs.getGameState() == 2) {
			if (key == KeyEvent.VK_P) {
				gs.setGameState(1);
			}
		} else if (gs.getGameState() == 3) {
			if (key == KeyEvent.VK_ENTER) {
				this.dialogueIndex++;
			}
		} else if (gs.getGameState() == 4) {

		} else if (gs.getGameState() == 5) {
			if (key == KeyEvent.VK_B) {
				//gs.setGameState(1);
			}
			else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				if (cmdNum > 1) {
					this.cmdNum -= 2;
				}
			} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
				if (cmdNum < 3) {
					this.cmdNum += 2;
				}
			} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
				if (cmdNum % 2 == 1) {
					this.cmdNum--;
				}
			} else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
				if (cmdNum % 2 == 0 && cmdNum != 4) {
					this.cmdNum++;
				}
			} else if (key == KeyEvent.VK_E) {
				this.interaction = true;
			}
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

	public int getDialogueIndex() {
		return this.dialogueIndex;
	}
	public void resetDialogueIndex() {
		this.dialogueIndex = 0;
	}

	public int getCmdNum() {
		return this.cmdNum;
	}
	
	public void finishBattle() {
		gs.setGameState(1);
	}
	
	
}