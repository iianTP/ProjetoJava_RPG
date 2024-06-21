package items;

public class Armor extends Item {
	public Armor(int type) {
		super.setEquipable();
		super.setRestriction(new String[] {"warrior", "assassin"});
		
		super.setName("teste");
		
		
	}
}
