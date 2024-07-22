package states.battle.spells;

import java.util.Random;

import entities.Battler;
import entities.Stats;
import states.battle.Battle;

public class WindBlades  extends Spell {
	
	private Random random = new Random();
	
	public WindBlades() {
		super.setSpellName("Laminas de Vento");
		super.setShortSpellName("L.VENT.");
		super.setManaCost(-7);
	}

	@Override
	public void castSpell(Battler target, Stats stats, Battle battle) {
		
		int spellDamage = stats.getMagic()+this.random.nextInt(5);
		int finalDamage = 2*spellDamage/target.getStats().getMagicDefense();
		
		target.takeDamage(finalDamage);
		stats.alterMana(super.getManaCost());
		
	}

	
}