package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Hypnosis  extends Spell {
	
	public Hypnosis() {
		super.setSpellName("Hipnose");
		super.setShortSpellName("HIPNO.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}