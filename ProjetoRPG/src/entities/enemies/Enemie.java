package entities.enemies;

import java.awt.image.BufferedImage;
import java.util.Random;

import entities.Player;
import entities.Stats;
import habilities.ICombat;

public abstract class Enemie implements ICombat {
	
	private Stats stats;
	private BufferedImage sprite;
	private final Random rng = new Random();
	
	private int attackChance = 10;
	private int defenseChance = 5;
	
	public int enemieRng(int range, int minNum) {
		return this.rng.nextInt(range) + minNum;
	}
	
	// MÉTODOS DE COMBATE
	
	@Override
	public <T> void attack(T target) {
		if (target instanceof Player) {
			((Player) target).takeDamage(this.stats.getStrenght());
			this.increaseAttackChance();
		}
	}

	@Override
	public <T> void magic(T target, int spellId) {
		if (target instanceof Player) {
			
		}
	}
	
	@Override
	public void defend() {
		if (this.stats.getHealth() < this.stats.getMaxHealth()) {
			this.stats.heal(this.enemieRng(this.stats.getDefense(), 0));
			if (this.stats.getHealth() > this.stats.getMaxHealth()) {
				this.stats.setHealth(this.stats.getMaxHealth());
			}
		}
	}
	
	@Override
	public void takeDamage(int damage) {
		this.stats.damage(damage);
		this.increaseDefenseChance();
	}
	
	//
	
	public int getAttackChance() {
		return attackChance;
	}
	public void increaseAttackChance() {
		this.attackChance += 2;
	}
	public void increaseDefenseChance() {
		this.defenseChance += 1;
	}
	
	public int getFullChance() {
		return attackChance + defenseChance;
	}
	
	public Stats getStats() {
		return this.stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public BufferedImage getSprite() {
		return this.sprite;
	}
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	

}
