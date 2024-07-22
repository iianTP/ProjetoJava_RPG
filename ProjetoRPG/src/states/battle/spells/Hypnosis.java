package states.battle.spells;

import entities.Battler;
import entities.Stats;
import states.battle.Battle;

public class Hypnosis  extends Spell {
	
	public Hypnosis() {
		super.setSpellName("Hipnose");
		super.setShortSpellName("HIPNO.");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		stats.alterMana(super.getManaCost());
		if (target.getEffects().getCurrentEffect().equals("none")) {
			target.getEffects().setCurrentEffect("hypnotized");
		}
		
	}

	
}