package combat.spells;

import java.util.Random;

import entities.Battler;
import entities.Stats;
import states.Battle;

public class Lightning  extends Spell {
	
	private Random random = new Random();
	
	public Lightning() {
		super.setSpellName("Relampago");
		super.setShortSpellName("RELAMP.");
		super.setManaCost(-5);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		int spellDamage = stats.getMagic()+this.random.nextInt(5);
		int finalDamage = 2*spellDamage/target.getStats().getMagicDefense();
		
		target.takeMagicDamage(finalDamage);
		
		stats.alterMana(super.getManaCost());
		if (target.getEffects().getCurrentEffect().equals("none")) {
			target.getEffects().setCurrentEffect("paralyzed");
		}
		
	}

	
}