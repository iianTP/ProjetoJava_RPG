package entities.enemies;

import java.awt.image.BufferedImage;

import entities.Player;
import entities.Stats;
import habilities.IHabilities;

public abstract class Enemie implements IHabilities {
	
	private Stats stats;
	private BufferedImage sprite;
	
	private int attackChance = 10;
	private int defenseChance = 5;
	
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
	
	// MÉTODOS DE COMBATE
	
	@Override
	public <T> void attack(T target) {
		if (target instanceof Player) {
			((Player) target).takeDamage(this.stats.getStrenght());
			this.increaseAttackChance();
		}
	}

	@Override
	public <T> void magic(T target) {
		if (target instanceof Player) {
			
		}
	}
	
	@Override
	public void defend() {
		
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
	

}
