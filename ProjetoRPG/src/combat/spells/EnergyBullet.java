package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class EnergyBullet  extends Spell {
	
	public EnergyBullet() {
		super.setSpellName("Disparo de Energia");
		super.setShortSpellName("D.ENERG.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}