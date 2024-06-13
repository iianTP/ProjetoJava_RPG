package entities;

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
	
	public void damage(int damage) {
		if (damage >= 0) {
			this.health -= damage;
		}
	}
	
	public void heal(int heal) {
		if (heal >= 0) {
			this.health += heal;
		}
	}
	
	public void buffStats() {
		this.maxHealth += 5;
		this.maxMana += 5;
	}
	
	// SET
	public void setStrenght(int strenght) {
		if (strenght >= 0) {
			this.strenght = strenght;
		}
	}
	public void setDefense(int defense) {
		if (defense >= 0) {
			this.defense = defense;
		}
	}
	public void setAgility(int agility) {
		if (agility >= 0) {
			this.agility = agility;
		}
	}
	public void setCriticalDamage(int criticalDamage) {
		if (criticalDamage >= 0) {
			this.criticalDamage = criticalDamage;
		}
	}
	public void setMana(int mana) {
		if (mana >= 0) {
			this.mana = mana;
		}
	}
	public void setMaxMana(int maxMana) {
		if (maxMana >= 0) {
			this.maxMana = maxMana;
		}
	}
	public void setMagic(int magic) {
		if (magic >= 0) {
			this.magic = magic;
		}
	}
	public void setMagicDefense(int magicDefense) {
		if (magicDefense >= 0) {
			this.magicDefense = magicDefense;
		}
	}
	public void setHealth(int health) {
		if (health >= 0) {
			this.health = health;
		}
	}
	public void setMaxHealth(int maxHealth) {
		if (maxHealth >= 0) {
			this.maxHealth = maxHealth;
		}
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
