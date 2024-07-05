package items;

import entities.Entity;
import entities.teammates.Teammate;

public class Item {
	
	private String name;
	private String description;
	private String[] restriction;
	
	private boolean consumable = false;
	private boolean equipable = false;
	
	
	public boolean checkRestriction(Entity entity) {
		
		if (this.restriction == null) {
			return true;
		}
		
		String[] splitedClassStr = entity.getClass().getName().split("\\.");
		String className = splitedClassStr[splitedClassStr.length-1].toLowerCase();
		if (entity instanceof Teammate) {
			className = className.replaceAll("npc", "");
		}
		
		for (int i = 0 ; i < this.restriction.length; i++) {
			if (this.restriction[i].equals(className)) {
				return true;
			}
		}
		return false;
	}
	
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
