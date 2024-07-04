package entities.npcs.questNpc;

import entities.npcs.Npc;
import main.screen.GameScreen;
import quests.Quest;

public abstract class QuestNpc extends Npc {

	private Quest quest;
	private boolean questRequested = false;
	private boolean rewarded = false;

	public QuestNpc(GameScreen gs) {
		super(gs);
	}

	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public boolean isQuestRequested() {
		return questRequested;
	}
	public void requestedQuest() {
		this.questRequested = true;
	}

	public boolean isRewarded() {
		return rewarded;
	}
	public void setRewarded() {
		this.rewarded = true;
	}

}
