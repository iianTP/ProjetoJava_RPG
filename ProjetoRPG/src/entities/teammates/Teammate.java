package entities.teammates;

import combat.BattleRng;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import exceptions.InvalidStatsInputException;
import exceptions.InvalidTargetException;
import interfaces.ICombat;
import items.Item;
import main.screen.GameScreen;

public abstract class Teammate extends Team implements ICombat {
	
	private BattleRng battleRng = new BattleRng();
	
	private Stats stats;

	public Teammate(GameScreen gs) {
		super(gs);
	}
	
	public void battleMove(Enemie enemie) {
		
		int randomNumber = this.battleRng.rng(this.battleRng.getFullChance(), 1);
		
		if (randomNumber <= this.battleRng.getAttackChance()) {
			try {
				this.attack(enemie);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
		} else {
			this.defend();
		}
		
	}
	
	@Override
	public <T> void attack(T target) throws InvalidTargetException {
		if (target instanceof Enemie) {
			((Enemie) target).takeDamage(this.stats.getStrenght());
			this.battleRng.increaseAttackChance();
		} else {
			throw new InvalidTargetException("alvo não é do tipo Enemie");
		}
	}

	@Override
	public <T> void magic(T target, int spellId) throws InvalidTargetException {
		if (target instanceof Enemie) {
			
		} else {
			throw new InvalidTargetException("alvo não é do tipo Enemie");
		}
	}
	
	@Override
	public void defend() {
		if (this.stats.getHealth() < this.stats.getMaxHealth()) {
			try {
				this.stats.heal(this.battleRng.rng(this.stats.getDefense(), 0));
				if (this.stats.getHealth() > this.stats.getMaxHealth()) {
					this.stats.setHealth(this.stats.getMaxHealth());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void takeDamage(int damage) {
		try {
			this.stats.damage(damage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.battleRng.increaseDefenseChance();
	}
	
	
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public abstract String getName();

}
