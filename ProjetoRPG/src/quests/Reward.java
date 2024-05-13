package quests;

import items.Item;

public class Reward {
	private Item[] items;
	private int gold;
	
	public Item[] getItems() {
		return this.items;
	}
	public void setItems(Item[] items) {
		this.items = items;
	}
	public int getGold() {
		return this.gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
}
