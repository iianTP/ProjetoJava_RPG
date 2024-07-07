package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Poison  extends Spell {
	
	public Poison() {
		super.setSpellName("Veneno");
		super.setShortSpellName("VENENO");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		stats.alterMana(super.getManaCost());
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("poisoned");
		}
		
	}

	
}