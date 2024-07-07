package entities.enemies;

import java.awt.image.BufferedImage;
import java.util.Random;

import combat.BattleRng;
import combat.Effects;
import entities.Stats;
import entities.player.Player;
import entities.teammates.Team;
import exceptions.InvalidStatsInputException;
import exceptions.InvalidTargetException;
import interfaces.ICombat;

public abstract class Enemie implements ICombat {
	
	private Stats stats;
	private BufferedImage sprite;
	private Effects effects = new Effects(this.stats);
	
	private BattleRng battleRng = new BattleRng();
	
	// MÉTODOS DE COMBATE
	
	public void battleMove(Team target) {
		
		int randomNumber = this.battleRng.rng(this.battleRng.getFullChance(), 1);
		
		if (randomNumber <= this.battleRng.getAttackChance()) {
			try {
				this.attack(target);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
		} else {
			this.defend();
		}
		
	}
	
	@Override
	public <T> void attack(T target) throws InvalidTargetException {
		if (target instanceof Team) {
			((Team) target).takeDamage(this.stats.getStrenght(), this.stats.getCriticalDamage());
			this.battleRng.increaseAttackChance();
		} else {
			throw new InvalidTargetException("alvo não é do tipo Team");
		}
	}

	@Override
	public <T> void magic(T target, int spellId) throws InvalidTargetException {
		if (target instanceof Team) {
			
		} else {
			throw new InvalidTargetException("alvo não é do tipo Team");
		}
	}
	
	@Override
	public void defend() {
		if (this.stats.getHealth() < this.stats.getMaxHealth()) {
			/*try {
				this.stats.heal(this.battleRng.rng(this.stats.getDefense(), 0));
				if (this.stats.getHealth() > this.stats.getMaxHealth()) {
					this.stats.setHealth(this.stats.getMaxHealth());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}*/
		}
	}
	
	@Override
	public void takeDamage(int damage, int criticalChance) {
		int defense = this.stats.getDefense();
		int critical = (this.battleRng.rng(100, 1) <= criticalChance) ? 2 : 1;
		int finalDamage = critical*2*damage/defense;
		try {
			this.stats.damage(finalDamage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.battleRng.increaseDefenseChance();
	}
	
	@Override
	public void takeMagicDamage(int magicDamage) {
		int magicDefense = this.stats.getMagicDefense();
		int finalDamage = 2*magicDamage/magicDefense;
		try {
			this.stats.damage(finalDamage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
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
