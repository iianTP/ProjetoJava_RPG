package interfaces;

import entities.Battler;
import entities.Stats;
import states.battle.Battle;

public interface ISpell {

	public void castSpell(Battler target, Stats stats, Battle battle);
	
}
