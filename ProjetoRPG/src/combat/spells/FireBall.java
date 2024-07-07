package combat.spells;

import entities.Battler;
import entities.Stats;

public class FireBall  extends Spell {
	
	public FireBall() {
		super.setSpellName("Bola de Fogo");
		super.setShortSpellName("B.FOGO");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("burning");
		}
		
	}

	
}