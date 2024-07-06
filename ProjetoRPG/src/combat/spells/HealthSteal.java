package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class HealthSteal  extends Spell {
	
	public HealthSteal() {
		super.setSpellName("Roubo de Vida");
		super.setShortSpellName("R.VIDA");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}