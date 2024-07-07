package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Curse extends Spell {
	
	public Curse() {
		super.setSpellName("Maldicao");
		super.setShortSpellName("MALDIC.");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		stats.alterMana(super.getManaCost());
		
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("cursed");
		}
		
	}

	
}