package interfaces;

import exceptions.InvalidTargetException;

public interface ICombat {
	
	public <T> void attack(T target) throws InvalidTargetException;
	
	public <T> void magic(T target, int spellId) throws InvalidTargetException;
	
	public void defend();

	public <T> void special(T target);
	
	public void takeDamage(int damage);
	
}
