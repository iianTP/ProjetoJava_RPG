package states.battle.spells;

import entities.Battler;
import entities.Stats;
import states.battle.Battle;

public class Curse extends Spell {
	
	public Curse() {
		super.setSpellName("Maldicao");
		super.setShortSpellName("MALDIC.");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		stats.alterMana(super.getManaCost());
		if (target.getEffects().getCurrentEffect().equals("none")) {
			target.getEffects().setCurrentEffect("cursed");
		}
		
	}

	
}