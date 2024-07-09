package quests;

import items.Item;

public class Reward {
	
	private Item item;
	private int gold;
	
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
