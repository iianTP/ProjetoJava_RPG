package quests;

import entities.enemies.Enemie;
import entities.npcs.questNpc.QuestNpc;
import main.DynamicArray;

public class QuestList extends DynamicArray<Quest>{

	public void addQuest(Quest quest) {
		super.addData(quest);
		System.out.println(quest.getType());
	}
	
	public Quest getQuest(int index) {
		return super.getData(index);
	}
	
	public void removeQuest(int index) {
		if (this.getQuest(index) != null) {
			super.removeData(index);
		}
	}
	
	public void checkKillEnemiesQuests(Enemie enemie, String winner) {
		if (winner.equals("player")) {
			int i = 0;
			Quest quest = null;
			quest = this.getQuest(i);
			while (quest != null) {
				if (quest instanceof KillEnemieQuest) {
					((KillEnemieQuest) quest).checkGoal(enemie);
					if (quest.isDone()) {
						System.out.println("done");
						break;
					}
				}
				i++;
				quest = this.getQuest(i);
			}
		}
	}
	
	public Quest getQuestByRequester(QuestNpc requester) {
		int i = 0;
		Quest quest = null;
		quest = this.getQuest(i);
		while (quest != null) {
			if (quest.getRequester().getClass() == requester.getClass()) {
				break;
			}
			i++;
			quest = this.getQuest(i);
		}
		
		return (quest != null) ? quest : null;
	}
	
}
