package combat.spells;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class Hypnosis  extends Spell {
	
	public Hypnosis() {
		super.setSpellName("Hipnose");
		super.setShortSpellName("HIPNO.");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats, Battle battle) {
		stats.alterMana(super.getManaCost());
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("hypnotized");
		}
	}

	
}