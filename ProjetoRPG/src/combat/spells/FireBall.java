package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class FireBall  extends Spell {
	
	public FireBall() {
		super.setSpellName("Bola de Fogo");
		super.setShortSpellName("B.FOGO");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("burning");
		}
		
	}

	
}