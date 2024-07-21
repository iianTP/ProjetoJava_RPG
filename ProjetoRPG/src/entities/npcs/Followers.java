package entities.npcs;

import entities.player.Player;
import main.screen.GameScreen;

public abstract class Followers extends Npc {
	
	public int getState() {
		return state;
	}

	private String[][] dialogue;
	private int state = 0;

	public Followers(GameScreen gs) {
		super(gs);
	}
	
	public void followerDialogue(Player player, int higherStage) {
		
		super.setDialogue(this.dialogue[0]);
		if (player.getGameStage() == higherStage) {
			this.state++;
			super.setDialogue(this.dialogue[1]);
		}
		super.getGs().setDialogueState();
		
	}

	public void setDialogue(String[][] dialogue) {
		this.dialogue = dialogue;
	}

}
