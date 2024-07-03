package interfaces;

public interface ICombat {
	
	public <T> void attack(T target);
	
	public <T> void magic(T target, int spellId);
	
	public void defend();

	public <T> void special(T target);
	
	public void takeDamage(int damage);
	
}
