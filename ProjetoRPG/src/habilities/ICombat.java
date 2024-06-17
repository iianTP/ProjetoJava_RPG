package habilities;

public interface ICombat {
	
	public <T> void attack(T target);
	
	public <T> void magic(T target, String spellId);
	
	public void defend();

	//public <T> void special();
	
	public void takeDamage(int damage);
	
}
