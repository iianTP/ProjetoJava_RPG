package entities.npcs.teammates;

import combat.BattleRng;
import entities.Stats;
import entities.npcs.Npc;
import items.Item;
import main.screen.GameScreen;

public abstract class Teammate extends Npc {
	
	private Item armorEquiped;
	private Item weaponEquiped;
	
	private BattleRng battleRng = new BattleRng();
	
	private Stats stats;

	public Teammate(GameScreen gs) {
		super(gs);
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
