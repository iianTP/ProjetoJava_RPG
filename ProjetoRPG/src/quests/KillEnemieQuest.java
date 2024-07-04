package quests;

import entities.enemies.Enemie;
import entities.npcs.questNpc.QuestNpc;

public class KillEnemieQuest extends Quest {

	private Enemie enemie;
	private int quantity;
	private int killed = 0;
	
	public KillEnemieQuest(Enemie enemie, int quantity, String type, Reward reward, QuestNpc requester) {
		super(type, reward, requester);
		this.enemie = enemie;
		this.quantity = quantity;
		super.setDescription("MATE ");
	}

	public void checkGoal(Enemie enemie) {
		if (enemie.getClass() == this.enemie.getClass()) {
			this.killed++;
			if (this.killed == this.quantity) {
				super.questComplete();
			}
		}
	}
	
}
