package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Lightning  extends Spell {
	
	public Lightning() {
		super.setSpellName("Relampago");
		super.setShortSpellName("RELAMP.");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		if (enemie.getEffects().getCurrentEffect().equals("none")) {
			enemie.getEffects().setCurrentEffect("paralyzed");
		}
		
	}

	
}