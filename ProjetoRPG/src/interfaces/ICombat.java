package interfaces;

import exceptions.InvalidTargetException;
import states.Battle;

public interface ICombat {
	
	public <T> void attack(T target, Battle battle) throws InvalidTargetException;
	
	public <T> void magic(T target, int spellId, Battle battle) throws InvalidTargetException;
	
	public void defend(Battle battle);

	public <T> void special(T target, Battle battle);
	
	public void takeDamage(int damage);
	
	public void takeMagicDamage(int magicDamage);
	
	public void setStats();
	
}
