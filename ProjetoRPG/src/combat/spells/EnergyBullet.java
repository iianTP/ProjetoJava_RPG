package combat.spells;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class EnergyBullet  extends Spell {
	
	public EnergyBullet() {
		super.setSpellName("Disparo de Energia");
		super.setShortSpellName("D.ENERG.");
		super.setManaCost(-3);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats, Battle battle) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}