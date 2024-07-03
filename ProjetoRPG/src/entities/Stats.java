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
	private int magicDefense;
	private int health;
	private int maxHealth;
	
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
		return this.strenght;
	}
	public int getDefense() {
		return this.defense;
	}
	public int getAgility() {
		return agility;
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
		return this.magic;
	}
	public int getMagicDefense() {
		return this.magicDefense;
	}
	public int getHealth() {
		return this.health;
	}
	public int getMaxHealth() {
		return this.maxHealth;
	}
	//
	
}
