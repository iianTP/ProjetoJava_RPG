package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Hypnosis  extends Spell {
	
	public Hypnosis() {
		super.setSpellName("Hipnose");
		super.setShortSpellName("HIPNO.");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		stats.alterMana(super.getManaCost());
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("hypnotized");
		}
	}

	
}