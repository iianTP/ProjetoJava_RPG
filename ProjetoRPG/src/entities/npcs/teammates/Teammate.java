package entities.npcs.teammates;

import combat.BattleRng;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import interfaces.ICombat;
import items.Item;
import main.screen.GameScreen;

public abstract class Teammate extends Npc implements ICombat {
	
	private Item armorEquiped;
	private Item weaponEquiped;
	
	private BattleRng battleRng = new BattleRng();
	
	private Stats stats;

	public Teammate(GameScreen gs) {
		super(gs);
	}
	
	public void battleMove(Enemie enemie) {
		
		int randomNumber = this.battleRng.rng(this.battleRng.getFullChance(), 1);
		
		if (randomNumber <= this.battleRng.getAttackChance()) {
			this.attack(enemie);
		} else {
			this.defend();
		}
		
	}
	
	@Override
	public <T> void attack(T target) {
		if (target instanceof Enemie) {
			((Enemie) target).takeDamage(this.stats.getStrenght());
			this.battleRng.increaseAttackChance();
		}
	}

	@Override
	public <T> void magic(T target, int spellId) {
		if (target instanceof Enemie) {
			
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
	
	
	public Item getArmorEquiped() {
		return armorEquiped;
	}
	public Item getWeaponEquiped() {
		return weaponEquiped;
	}
	public void setArmorEquiped(Item armorEquiped) {
		this.armorEquiped = armorEquiped;
	}
	public void setWeaponEquiped(Item weaponEquiped) {
		this.weaponEquiped = weaponEquiped;
	}
	
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}

}
