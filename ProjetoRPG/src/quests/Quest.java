package quests;

import entities.npcs.Npc;
import entities.player.Player;
import exceptions.InventoryIsFullException;
import items.Item;

public abstract class Quest {
	
	private String type;
	private String description;
	
	private Npc requester;
	
	private Reward reward;
	
	private boolean done = false;
	
	private int id = -1;
	
	public Quest(String type, Reward reward, Npc requester) {
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

	public void redeemReward(Player player) {
		if (reward.getItem() != null) {
			Item items = reward.getItem();
			if (!player.getInventory().isFull()) {
				player.getInventory().addItem(items);
			}
		}
		player.addGold(reward.getGold());
	}

	public Npc getRequester() {
		return requester;
	}
	public Reward getReward() {
		return reward;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
