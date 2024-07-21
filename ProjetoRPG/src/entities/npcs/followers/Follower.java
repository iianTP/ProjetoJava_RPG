package entities.npcs.followers;

import entities.npcs.Npc;
import entities.player.Player;
import main.screen.GameScreen;

public abstract class Follower extends Npc {
	
	public int getState() {
		return state;
	}

	private String[][] dialogue;
	private int state = 0;

	public Follower(GameScreen gs) {
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
