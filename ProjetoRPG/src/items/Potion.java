package items;

import entities.Stats;
import exceptions.InvalidStatsInputException;

public class Potion extends Item {
	
	private int type;
	
	private int strength;
	private int agility;
	private int magic;
	private int health;
	private int mana;
	
	public Potion(int type) {
		super.setConsumable();
		
		this.type = type;
		
		switch(type) {
		case 1:
			super.setName("Pocao de cura");
			this.health = 5;
			break;
		case 2:
			super.setName("Pocao de mana");
			this.mana = 2;
			break;
		case 3:
			super.setName("Pocao de forca");
			this.strength = 3;
			break;
		case 4:
			super.setName("Pocao de agilidade");
			this.agility = 4;
			break;
		case 5:
			super.setName("Pocao de magia");
			this.magic = 4;
			break;
		}
	}
	
	public void consumePotion(Stats stats) {
		
		switch (this.type) {
		case 1:
			try {
				stats.heal(this.health);
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			stats.alterMana(this.mana);
			this.mana = 2;
			break;
		case 3:
			stats.setPotionStrenght(this.strength);
			break;
		case 4:
			stats.setPotionAgility(this.agility);
			break;
		case 5:
			stats.setPotionMagic(this.magic);
			break;
		}
		
	}
	
	public void stopEffect(Stats stats) {
		
		switch (this.type) {
		case 3:
			stats.setPotionStrenght(0);
			break;
		case 4:
			stats.setPotionAgility(0);
			break;
		case 5:
			stats.setPotionMagic(0);
			break;
		}
		
	}
	
	public int getType() {
		return type;
	}
	
}
