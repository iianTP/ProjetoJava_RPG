package states;

import javax.sound.sampled.Clip;

import main.KeyInput;
import main.screen.GameScreen;

public class MainMenu {
	
	private KeyInput key;
	private GameScreen gs;
	
	private String state = "main";
	
	public MainMenu(KeyInput key, GameScreen gs) {
		this.key = key;
		this.gs = gs;
		this.key.setMaxCmdNum(2);
	}
	
	public void mainMenu() {
		
		if (this.key.isInteracting()) {
			if (this.state.equals("main")) {
				this.mainOptions();
			} else if (this.state.equals("choose-class")) {
				this.chooseClass();
			} else if (this.state.equals("instructions")) {
				this.state = "main";
				this.key.setMaxCmdNum(2);
			}
		}
		
	}
	
	private void mainOptions() {
		switch (this.key.getCmdNum()) {
		case 0:
			this.state = "choose-class";
			this.key.setMaxCmdNum(3);
			this.key.resetCmdNum();
			break;
		case 1:
			this.state = "instructions";
			this.key.setMaxCmdNum(0);
			this.key.resetCmdNum();
			break;
		case 2:
			System.exit(0);
			break;
		}
	}
	
	private void chooseClass() {
		String playerClass = null;
		
		switch (this.key.getCmdNum()) {
		case 0:
			playerClass = "mage";
			break;
		case 1:
			playerClass = "warrior";
			break;
		case 2:
			playerClass = "healer";
			break;
		case 3:
			playerClass = "assassin";
			break;
		}
		
		gs.startGame(playerClass);
		gs.setIntroState();
		this.key.resetCmdNum();
		
	}

	public String getState() {
		return this.state;
	}
	
}
