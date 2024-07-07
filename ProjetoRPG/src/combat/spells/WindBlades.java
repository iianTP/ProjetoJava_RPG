package combat.spells;

import entities.Battler;
import entities.Stats;

public class WindBlades  extends Spell {
	
	public WindBlades() {
		super.setSpellName("Laminas de Vento");
		super.setShortSpellName("L.VENT.");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}