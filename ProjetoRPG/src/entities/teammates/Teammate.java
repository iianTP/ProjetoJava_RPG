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
		Stats stats = super.getStats();
		if (target instanceof Enemie) {
			((Enemie) target).takeDamage(stats.getStrenght(), stats.getCriticalDamage());
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
		Stats stats = super.getStats();		
		if (stats.getHealth() < stats.getMaxHealth()) {
			try {
				stats.heal(this.battleRng.rng(stats.getDefense(), 0));
				if (stats.getHealth() > stats.getMaxHealth()) {
					stats.setHealth(stats.getMaxHealth());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void takeDamage(int damage, int criticalChance) {
		Stats stats = super.getStats();
		int defense = this.getStats().getDefense();
		int critical = (this.battleRng.rng(100, 1) <= criticalChance) ? 2 : 1;
		int finalDamage = critical*2*damage/defense;
		try {
			stats.damage(finalDamage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.battleRng.increaseDefenseChance();
	}
	
	@Override
	public void takeMagicDamage(int magicDamage) {
		Stats stats = super.getStats();
		try {
			stats.damage(magicDamage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
	}
	
	public abstract String getName();

}
