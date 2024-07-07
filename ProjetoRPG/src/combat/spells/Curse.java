package combat.spells;

import entities.Battler;
import entities.Stats;

public class Curse extends Spell {
	
	public Curse() {
		super.setSpellName("Maldicao");
		super.setShortSpellName("MALDIC.");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats) {
		
		stats.alterMana(super.getManaCost());
		
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("cursed");
		}
		
	}

	
}