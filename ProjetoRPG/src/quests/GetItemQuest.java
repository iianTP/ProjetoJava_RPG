package quests;

import items.Item;

public class GetItemQuest extends Quest {
	
	private Item item;
	
	public GetItemQuest(Item item, String type, Reward reward) {
		super(type, reward, null);
		this.item = item;
		super.setDescription("PROVIDENCIE "+item.getName().toUpperCase());
	}

	public void checkGoal(Item item) {
		if (item.getClass() == this.item.getClass()) {
			super.questComplete();
		}
	}

}
