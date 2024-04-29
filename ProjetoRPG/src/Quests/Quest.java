package Quests;

public class Quest {
	
	private String name;
	private String type;
	private String description;
	private String reward;
	
	private boolean done = false;

	// SET
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public void setDone() {
		this.done = true;
	}

	// GET
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	public String getDescription() {
		return this.description;
	}
	public String getReward() {
		return this.reward;
	}

	// IS
	public boolean isDone() {
		return this.done;
	}

}
