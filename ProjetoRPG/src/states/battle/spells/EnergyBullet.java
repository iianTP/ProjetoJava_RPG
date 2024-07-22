package states.battle.spells;

import entities.Battler;
import entities.Stats;
import states.battle.Battle;

public class EnergyBullet  extends Spell {
	
	public EnergyBullet() {
		super.setSpellName("Disparo de Energia");
		super.setShortSpellName("D.ENERG.");
		super.setManaCost(-3);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		int spellDamage = stats.getMagic();
		int finalDamage = 2*spellDamage/target.getStats().getMagicDefense();
		
		target.takeDamage(finalDamage);
		stats.alterMana(super.getManaCost());
		
	}

	
}