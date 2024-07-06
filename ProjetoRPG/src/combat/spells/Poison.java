package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Poison  extends Spell {
	
	public Poison() {
		super.setSpellName("Veneno");
		super.setShortSpellName("VENENO");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}