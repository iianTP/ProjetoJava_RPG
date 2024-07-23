package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import exceptions.InvalidStatsInputException;

public class Potion extends Item {
	
	private int strength;
	private int agility;
	private int magic;
	private int health;
	private int mana;
	
	private String properties = null;
	
	public Potion(int type) {
		super.setConsumable();
		
		super.setType(type);
		
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/items/potion_"+(type-1)+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		super.setDescription("Pocao magica que pode ajudar em suas batalhas.");
		
		switch(type) {
		case 1:
			super.setName("Pocao de cura");
			this.health = 5;
			this.properties = "HP: +5";
			super.setSellPrice(10);
			break;
		case 2:
			super.setName("Pocao de mana");
			this.mana = 2;
			this.properties = "MANA: +2";
			super.setSellPrice(10);
			break;
		case 3:
			super.setName("Pocao de forca");
			this.strength = 3;
			this.properties = "ATQ: +3";
			super.setSellPrice(10);
			break;
		case 4:
			super.setName("Pocao de agilidade");
			this.agility = 4;
			this.properties = "AGL: +4";
			super.setSellPrice(10);
			break;
		case 5:
			super.setName("Pocao de magia");
			this.magic = 4;
			this.properties = "MGC: +4";
			super.setSellPrice(10);
			break;
		}
	}
	
	public void consumePotion(Stats stats) {
		
		switch (super.getType()) {
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
	
	@Override
	public String getProperties() {
		return this.properties;
	}
	

	
}
