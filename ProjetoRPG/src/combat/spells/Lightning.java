package combat.spells;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class Lightning  extends Spell {
	
	public Lightning() {
		super.setSpellName("Relampago");
		super.setShortSpellName("RELAMP.");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats, Battle battle) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("paralyzed");
		}
		
	}

	
}