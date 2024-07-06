package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class FireBall  extends Spell {
	
	public FireBall() {
		super.setSpellName("Bola de Fogo");
		super.setShortSpellName("B.FOGO");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}