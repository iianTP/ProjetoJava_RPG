package combat.spells;

import entities.Battler;
import entities.Entity;
import entities.Stats;
import states.Battle;

public class BloodSpear extends Spell {
	
	public BloodSpear() {
		super.setSpellName("Lanca de Sangue");
		super.setShortSpellName("L.SANG.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats, Battle battle) {
		if (stats.getMana() >= -super.getManaCost()) {
			
			enemie.takeMagicDamage(stats.getMagic());
			stats.alterMana(super.getManaCost());
			
			if (enemie.getEffects().getCurrentEffect().equals("none")) {
				enemie.getEffects().setCurrentEffect("bleeding");
			}
		}
	}

	
}
