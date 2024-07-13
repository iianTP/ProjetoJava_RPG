package interfaces;

import entities.Battler;
import entities.Entity;
import entities.Stats;
import states.Battle;

public interface ISpell {

	public void castSpell(Battler target, Stats stats, Battle battle);
	
}
