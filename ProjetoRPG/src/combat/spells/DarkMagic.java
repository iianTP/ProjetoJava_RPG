package combat.spells;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class DarkMagic  extends Spell {
	
	private int damage = 1;
	
	public DarkMagic() {
		super.setSpellName("Magia Sombria");
		super.setShortSpellName("M.SOMBR.");
		super.setManaCost(-10);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		target.takeMagicDamage(this.damage);
		this.damage *= 2;
		stats.alterMana(super.getManaCost());
		
	}

	public void resetDamage() {
		this.damage = 0;
	}
}