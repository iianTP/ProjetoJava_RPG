package quests;

import entities.enemies.Enemie;
import items.Item;
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
		System.out.println("a");
		if (winner != null && winner.equals("player")) {
			int i = 0;
			Quest quest = null;
			quest = this.getQuest(i);
			while (quest != null) {
				if (quest instanceof KillEnemieQuest) {
					
					((KillEnemieQuest) quest).checkGoal(enemie);
					if (quest.isDone()) {
						quest.setId(i);
						break;
					}
				}
				i++;
				quest = this.getQuest(i);
			}
		}
	}

	public void checkGetItemsQuests(Item item) {
		int i = 0;
		Quest quest = this.getQuest(i);
		while (quest != null) {
			if (quest instanceof GetItemQuest) {
				((GetItemQuest) quest).checkGoal(item);
				if (quest.isDone()) {
					quest.setId(i);
					break;
				}
			}
			i++;
			quest = this.getQuest(i);
		}
	}
	
}
