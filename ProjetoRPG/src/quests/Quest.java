package quests;

import entities.npcs.questNpc.QuestNpc;
import entities.player.Player;
import exceptions.InventoryIsFullException;
import items.Item;

public abstract class Quest {
	
	private String type;
	private String description;
	
	private QuestNpc requester;
	
	private Reward reward;
	
	private boolean done = false;
	
	public Quest(String type, Reward reward, QuestNpc requester) {
		this.type = type;
		this.reward = reward;
		this.requester = requester;
	}

	public void questComplete() {
		this.done = true;
	}

	public String getType() {
		return this.type;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}

	public boolean isDone() {
		return this.done;
	}

	public void redeemReward(Player player) throws InventoryIsFullException {
		if (reward.getItems() != null) {
			Item[] items = reward.getItems();
			for (int i = 0; i < items.length; i++) {
				if (!player.getInventory().isFull()) {
					player.getInventory().addItem(items[i]);
				}
			}
		}
		player.addGold(reward.getGold());
	}

	public QuestNpc getRequester() {
		return requester;
	}

}
