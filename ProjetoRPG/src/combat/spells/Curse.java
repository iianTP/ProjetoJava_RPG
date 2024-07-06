package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Curse extends Spell {
	
	public Curse() {
		super.setSpellName("Maldicao");
		super.setShortSpellName("MALDIC.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}