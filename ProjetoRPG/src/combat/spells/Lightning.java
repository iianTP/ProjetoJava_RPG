package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class Lightning  extends Spell {
	
	public Lightning() {
		super.setSpellName("Relampago");
		super.setShortSpellName("RELAMP.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}