package items;

import java.awt.image.BufferedImage;

import entities.teammates.Team;
import entities.teammates.Teammate;

public abstract class Item {
	
	private String name;
	private String shortName;
	private String description;
	private String[] restriction;
	
	private int sellPrice;
	
	private int type;
	
	private BufferedImage sprite;
	
	private boolean consumable = false;
	private boolean equipable = false;
	
	public boolean checkRestriction(Team entity) {
		
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
	public String getProperties() {
		return null;
	}
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

}
