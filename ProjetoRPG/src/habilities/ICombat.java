package habilities;

public interface ICombat {
	
	public <T> void attack(T target);
	
	public <T> void magic(T target, int spellId);
	
	public void defend();
	
	public void takeDamage(int damage);
	
}
