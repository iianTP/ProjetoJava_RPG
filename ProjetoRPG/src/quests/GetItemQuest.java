package quests;

import entities.npcs.Npc;
import entities.npcs.questNpc.QuestNpc;
import items.Item;

public class GetItemQuest extends Quest {
	
	private Item item;
	private Npc destiny;
	
	public GetItemQuest(Item item, Npc destiny, String type, Reward reward, QuestNpc requester) {
		super(type, reward, requester);
		this.item = item;
		this.destiny = destiny;
	}

	public void checkGoal(Npc destiny, Item item) {
		if (destiny.getClass() == this.destiny.getClass() && item.getClass() == this.item.getClass()) {
			super.questComplete();
		}
	}

}
