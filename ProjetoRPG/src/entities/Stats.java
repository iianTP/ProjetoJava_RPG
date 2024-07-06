package entities;

import exceptions.InvalidStatsInputException;

public class Stats {
	
	private int strenght;
	private int defense;
	private int agility;
	private int criticalDamage;
	private int mana;
	private int maxMana;
	private int magic;
	private int itemMagic;
	private int magicDefense;
	private int health;
	private int maxHealth;
	
	private int itemStrenght;
	private int itemDefense;
	private int itemAgility;
	private int itemMagicDefense;
	
	private int potionStrenght;
	private int potionAgility;
	private int potionMagic;
	
	public void damage(int damage) throws InvalidStatsInputException {
		if (damage < 0) {
			throw new InvalidStatsInputException("valor dano: "+damage+" < 0");
		}
		this.health -= damage;
	}
	
	public void heal(int heal) throws InvalidStatsInputException {
		if (heal < 0) {
			throw new InvalidStatsInputException("valor cura: "+heal+" < 0");
		}
		this.health += heal;
		System.out.println("heal");
	}
	
	public void alterMana(int mana) {
		this.mana += mana;
	}
	
	public void buffStats() {
		this.maxHealth += 5;
		this.maxMana += 5;
	}
	
	// SET
	public void setStrenght(int strenght) throws InvalidStatsInputException {
		if (strenght < 0) {
			throw new InvalidStatsInputException("valor força: "+strenght+" < 0");
		}
		this.strenght = strenght;
	}
	public void setDefense(int defense) throws InvalidStatsInputException {
		if (defense < 0) {
			throw new InvalidStatsInputException("valor defesa: "+defense+" < 0");
		}
		this.defense = defense;
	}
	public void setAgility(int agility) throws InvalidStatsInputException {
		if (agility < 0) {
			throw new InvalidStatsInputException("valor agilidade: "+agility+" < 0");
		}
		this.agility = agility;
	}
	public void setCriticalDamage(int criticalDamage) throws InvalidStatsInputException {
		if (criticalDamage < 0 || criticalDamage > 100) {
			throw new InvalidStatsInputException("valor dano critico: "+criticalDamage+" < 0 ou > 100");
		}
		this.criticalDamage = criticalDamage;
	}
	public void setMana(int mana) throws InvalidStatsInputException {
		if (mana < 0) {
			throw new InvalidStatsInputException("valor mana: "+mana+" < 0");
		}
		this.mana = mana;
	}
	public void setMaxMana(int maxMana) throws InvalidStatsInputException {
		if (maxMana < 0) {
			throw new InvalidStatsInputException("valor max mana: "+maxMana+" < 0");
		}
		this.maxMana = maxMana;
	}
	public void setMagic(int magic) throws InvalidStatsInputException {
		if (magic < 0) {
			throw new InvalidStatsInputException("valor magia: "+magic+" < 0");
		}
		this.magic = magic;
	}
	public void setMagicDefense(int magicDefense) throws InvalidStatsInputException {
		if (magicDefense < 0) {
			throw new InvalidStatsInputException("valor defesa magica: "+magicDefense+" < 0");
		}
		this.magicDefense = magicDefense;
	}
	public void setHealth(int health) throws InvalidStatsInputException {
		if (health < 0) {
			throw new InvalidStatsInputException("valor vida: "+health+" < 0");
		}
		this.health = health;
	}
	public void setMaxHealth(int maxHealth) throws InvalidStatsInputException {
		if (maxHealth < 0) {
			throw new InvalidStatsInputException("valor max vida: "+maxHealth+" < 0");
		}
		this.maxHealth = maxHealth;
	}
	//
	
	// GET
	public int getStrenght() {
		return this.strenght+this.itemStrenght+this.potionStrenght;
	}
	public int getDefense() {
		return this.defense+this.itemDefense;
	}
	public int getAgility() {
		return agility+this.itemAgility+this.potionAgility;
	}
	public int getCriticalDamage() {
		return criticalDamage;
	}
	public int getMana() {
		return this.mana;
	}
	public int getMaxMana() {
		return this.maxMana;
	}
	public int getMagic() {
		return this.magic+this.itemMagic+this.potionMagic;
	}
	public int getMagicDefense() {
		return this.magicDefense+this.itemMagicDefense;
	}
	public int getHealth() {
		return this.health;
	}
	public int getMaxHealth() {
		return this.maxHealth;
	}
	//

	public void setItemStrenght(int itemStrenght) throws InvalidStatsInputException {
		if (itemStrenght < 0) {
			throw new InvalidStatsInputException("valor itemStrenght: "+itemStrenght+" < 0");
		}
		this.itemStrenght = itemStrenght;
	}

	public void setItemDefense(int itemDefense) throws InvalidStatsInputException {
		if (itemDefense < 0) {
			throw new InvalidStatsInputException("valor itemDefense: "+itemDefense+" < 0");
		}
		this.itemDefense = itemDefense;
	}

	public void setItemAgility(int itemAgility) {
		this.itemAgility = itemAgility;
	}

	public void setItemMagic(int itemMagic) throws InvalidStatsInputException {
		if (itemMagic < 0) {
			throw new InvalidStatsInputException("valor itemMagic: "+itemMagic+" < 0");
		}
		this.itemMagic = itemMagic;
	}

	public void setItemMagicDefense(int itemMagicDefense) throws InvalidStatsInputException {
		if (itemMagicDefense < 0) {
			throw new InvalidStatsInputException("valor itemMagicDefense: "+itemMagicDefense+" < 0");
		}
		this.itemMagicDefense = itemMagicDefense;
	}

	public void setPotionStrenght(int potionStrenght) {
		this.potionStrenght = potionStrenght;
	}

	public void setPotionAgility(int potionAgility) {
		this.potionAgility = potionAgility;
	}

	public void setPotionMagic(int potionMagic) {
		this.potionMagic = potionMagic;
	}
	
}
