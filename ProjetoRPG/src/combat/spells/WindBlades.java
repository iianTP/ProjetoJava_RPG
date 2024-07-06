package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class WindBlades  extends Spell {
	
	public WindBlades() {
		super.setSpellName("Laminas de Vento");
		super.setShortSpellName("L.VENT.");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}