package interfaces;

import entities.Battler;
import entities.Entity;
import entities.Stats;

public interface ISpell {

	public void castSpell(Battler target, Stats stats);
	
}
