package combat.spells;

import entities.Battler;
import entities.Stats;

public class EnergyBullet  extends Spell {
	
	public EnergyBullet() {
		super.setSpellName("Disparo de Energia");
		super.setShortSpellName("D.ENERG.");
		super.setManaCost(-3);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}