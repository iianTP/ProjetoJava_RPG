package quests;

public class Quest {
	
	private String name;
	private String type;
	private String description;
	
	private Reward reward;
	
	private boolean done = false;
	
	public Quest(String name, String type, String description, Reward reward) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.reward = reward;
	}

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

	// IS
	public boolean isDone() {
		return this.done;
	}

}
