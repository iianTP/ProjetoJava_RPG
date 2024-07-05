package quests;

import entities.npcs.questNpc.QuestNpc;
import items.Item;

public class GetItemQuest extends Quest {
	
	private Item item;
	
	public GetItemQuest(Item item, String type, Reward reward, QuestNpc requester) {
		super(type, reward, requester);
		this.item = item;
	}

	public void checkGoal(Item item) {
		if (item.getClass() == this.item.getClass()) {
			super.questComplete();
		}
	}

}
