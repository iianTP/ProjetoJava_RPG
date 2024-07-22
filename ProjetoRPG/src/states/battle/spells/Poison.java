package states.battle.spells;

import entities.Battler;
import entities.Stats;
import states.battle.Battle;

public class Poison  extends Spell {
	
	public Poison() {
		super.setSpellName("Veneno");
		super.setShortSpellName("VENENO");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats, Battle battle) {
		
		stats.alterMana(super.getManaCost());
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("poisoned");
		}
		
	}

	
}