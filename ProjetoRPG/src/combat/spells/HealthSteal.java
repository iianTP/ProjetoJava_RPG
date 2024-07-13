package combat.spells;

import entities.Battler;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import states.Battle;

public class HealthSteal  extends Spell {
	
	public HealthSteal() {
		super.setSpellName("Roubo de Vida");
		super.setShortSpellName("R.VIDA");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		target.takeMagicDamage(stats.getMagic());
		
		if (stats.getHealth() < stats.getMaxHealth()) {
			try {
				stats.heal(stats.getMagic());
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
		}
		stats.alterMana(super.getManaCost());
		
	}

	
}