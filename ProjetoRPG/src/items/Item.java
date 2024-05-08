package items;

public class Item {
	
	private String name;
	private String description;
	private String[] restriction;
	
	private boolean consumable;
	private boolean equipable;
	
	// SET
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRestriction(String[] restriction) {
		this.restriction = restriction;
	}
	public void setConsumable() {
		this.consumable = true;
		this.equipable = false;
	}
	public void setEquipable() {
		this.consumable = false;
		this.equipable = true;
	}
	
	// GET
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public String[] getRestriction() {
		return this.restriction;
	}
	
	// IS
	public boolean isUsable() {
		return this.consumable;
	}
	public boolean isEquipable() {
		return this.equipable;
	}

}
