package combat.spells;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class FireBall  extends Spell {
	
	public FireBall() {
		super.setSpellName("Bola de Fogo");
		super.setShortSpellName("B.FOGO");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		int spellDamage = stats.getMagic();
		int finalDamage = 2*spellDamage/target.getStats().getMagicDefense();
		
		target.takeMagicDamage(finalDamage);
		stats.alterMana(super.getManaCost());
		
		if (target.getEffects().getCurrentEffect().equals("none")) {
			target.getEffects().setCurrentEffect("burning");
		}
		
	}

	
}