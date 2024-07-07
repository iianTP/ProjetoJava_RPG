package combat.spells;

import entities.Battler;
import entities.Stats;

public class Hypnosis  extends Spell {
	
	public Hypnosis() {
		super.setSpellName("Hipnose");
		super.setShortSpellName("HIPNO.");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats) {
		stats.alterMana(super.getManaCost());
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("hypnotized");
		}
	}

	
}