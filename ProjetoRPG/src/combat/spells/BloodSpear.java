package combat.spells;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class BloodSpear extends Spell {
	
	public BloodSpear() {
		super.setSpellName("Lanca de Sangue");
		super.setShortSpellName("L.SANG.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		if (stats.getMana() >= -super.getManaCost()) {
			
			int spellDamage = stats.getMagic();
			int finalDamage = 2*spellDamage/target.getStats().getMagicDefense();
			
			target.takeMagicDamage(finalDamage);
			stats.alterMana(super.getManaCost());
			
			if (target.getEffects().getCurrentEffect().equals("none")) {
				target.getEffects().setCurrentEffect("bleeding");
			}
		}
	}

	
}
