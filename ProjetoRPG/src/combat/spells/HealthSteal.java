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
		
		int enemieHpBefore = target.getStats().getHealth(); 
		target.takeMagicDamage(stats.getMagic());
		
		int finalDamage = stats.getMagic();
		
		battle.setMessage(battle.getMessage()+" (-"+finalDamage+"HP)");
		
		if (stats.getHealth() < stats.getMaxHealth()) {
			try {
				stats.heal(enemieHpBefore-target.getStats().getHealth());
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
		}
		stats.alterMana(super.getManaCost());
		
	}

	
}