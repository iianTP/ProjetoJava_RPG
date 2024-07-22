package interfaces;

import entities.Battler;
import exceptions.InvalidTargetException;
import states.battle.Battle;

public interface ICombat {
	
	public <T extends Battler> void attack(T target, Battle battle) throws InvalidTargetException;
	
	public <T extends Battler> void magic(T target, int spellId, Battle battle) throws InvalidTargetException;
	
	public void defend(Battle battle);

	public <T> void special(T target, Battle battle);
	
	public void takeDamage(int damage);
	
	public void setStats();
	
}
