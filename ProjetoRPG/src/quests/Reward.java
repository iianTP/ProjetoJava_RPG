package quests;

import items.Item;

public class Reward {
	
	private Item item;
	private int gold;
	
	public Reward(Item item, int gold) {
		this.item = item;
		this.gold = gold;
	}
	public Reward(int gold) {
		this.gold = gold;
	}
	public Reward(Item item) {
		this.item = item;
	}
	
	public String getRewardString() {
		String reward = "";
		if (this.item != null) {
			reward += "// "+this.item.getName()+" // ";
		}
		if (this.gold > 0) {
			reward += this.gold+"G //";
		}
		return reward;
	}
	
	public Item getItem() {
		return this.item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getGold() {
		return this.gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
}
