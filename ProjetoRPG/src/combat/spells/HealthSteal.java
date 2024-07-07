package combat.spells;

import entities.Battler;
import entities.Stats;
import exceptions.InvalidStatsInputException;

public class HealthSteal  extends Spell {
	
	public HealthSteal() {
		super.setSpellName("Roubo de Vida");
		super.setShortSpellName("R.VIDA");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats) {
		int enemieHpBefore = enemie.getStats().getHealth(); 
		enemie.takeMagicDamage(stats.getMagic());
		if (stats.getHealth() < stats.getMaxHealth()) {
			try {
				stats.heal(enemieHpBefore-enemie.getStats().getHealth());
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
		}
		stats.alterMana(super.getManaCost());
		
	}

	
}