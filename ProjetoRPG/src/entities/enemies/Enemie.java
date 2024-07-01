package entities.enemies;

import java.awt.image.BufferedImage;
import java.util.Random;

import combat.BattleRng;
import combat.Effects;
import entities.Stats;
import entities.player.Player;
import interfaces.ICombat;

public abstract class Enemie implements ICombat {
	
	private Stats stats;
	private BufferedImage sprite;
	private final Random rng = new Random();
	private Effects effects = new Effects(this.stats);
	
	private BattleRng battleRng = new BattleRng();
	
	// MÉTODOS DE COMBATE
	
	public void battleMove(Player player) {
		
		int randomNumber = this.battleRng.rng(this.battleRng.getFullChance(), 1);
		
		if (randomNumber <= this.battleRng.getAttackChance()) {
			this.attack(player);
		} else {
			this.defend();
		}
		
	}
	
	@Override
	public <T> void attack(T target) {
		if (target instanceof Player) {
			((Player) target).takeDamage(this.stats.getStrenght());
			this.battleRng.increaseAttackChance();
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
			this.stats.heal(this.battleRng.rng(this.stats.getDefense(), 0));
			if (this.stats.getHealth() > this.stats.getMaxHealth()) {
				this.stats.setHealth(this.stats.getMaxHealth());
			}
		}
	}
	
	@Override
	public void takeDamage(int damage) {
		this.stats.damage(damage);
		this.battleRng.increaseDefenseChance();
	}
	
	
	
	//
	
	
	
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

	public Effects getEffects() {
		return effects;
	}
	

}
