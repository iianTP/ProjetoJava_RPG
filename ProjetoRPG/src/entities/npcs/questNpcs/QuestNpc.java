package entities.npcs.questNpcs;

import entities.npcs.Npc;
import entities.player.Player;
import exceptions.IndexOutOfRangeException;
import items.Item;
import main.screen.GameScreen;
import quests.Quest;

public abstract class QuestNpc extends Npc {
	
	private String[][] dialogue;
	
	private Quest quest;
	private boolean requested = false;
	private boolean rewarded = false;

	public QuestNpc(GameScreen gs) {
		super(gs);
	}
	
	public void checkQuest(Player player) {
		
		if (player.getQuestList().isFullOfQuests()) {
			super.setDialogue(this.dialogue[2]);
			return;
		}
		
		if (!requested) {
			player.getQuestList().addQuest(quest);
			super.setDialogue(this.dialogue[0]);
			this.requested = true;
		} else {
			for (int i = 0; i < 10; i++) {
				Item item = null;
				try {
					item = player.getInventory().getItem(i);
				} catch (IndexOutOfRangeException e) {
					e.printStackTrace();
				}
				if (item == null) break;
				player.getQuestList().checkGetItemsQuests(item);
			}
		}
		
		if (this.quest.isDone() && !this.rewarded) {
			this.quest.redeemReward(player);
			player.getQuestList().removeQuest(this.quest.getId());
			super.setDialogue(this.dialogue[1]);
			this.rewarded = true;
		}
	}
	
	public void setQuestDialogue(String[][] dialogue) {
		this.dialogue = dialogue;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}
	
}
