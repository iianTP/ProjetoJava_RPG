package combat.spells;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class DarkMagic  extends Spell {
	
	public DarkMagic() {
		super.setSpellName("Magia Sombria");
		super.setShortSpellName("M.SOMBR.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Battler enemie, Stats stats, Battle battle) {
		
		enemie.takeMagicDamage(stats.getMagic());
		stats.alterMana(super.getManaCost());
		
	}

	
}