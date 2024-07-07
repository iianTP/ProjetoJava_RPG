package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;

public class DarkMagic  extends Spell {
	
	public DarkMagic() {
		super.setSpellName("Magia Sombria");
		super.setShortSpellName("M.SOMBR.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Enemie enemie, Stats stats) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}