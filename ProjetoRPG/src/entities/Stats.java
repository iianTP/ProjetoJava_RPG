package entities;

public class Stats {
	
	private int strenght;
	private int defense;
	private int mana;
	private int maxMana;
	private int magic;
	private int magicDefense;
	private int health;
	private int maxHealth;
	
	
	public void damage(int dmg) {
		this.health -= dmg;
	}
	
	
	// SET
	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public void setMana(int mana) {
		this.mana = mana;
	}
	public void setMaxMana(int maxMana) {
		this.maxMana = maxMana;
	}
	public void setMagic(int magic) {
		this.magic = magic;
	}
	public void setMagicDefense(int magicDefense) {
		this.magicDefense = magicDefense;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setMaxHealth(int maxHealth) {
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
	
	public void buffStats() {
		this.maxHealth += 5;
		this.maxMana += 5;
	}

}
