package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class BloodSpear extends Spell {
	
	public BloodSpear() {
		super.setSpellName("Lanca de Sangue");
		super.setShortSpellName("L.SANG.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}
