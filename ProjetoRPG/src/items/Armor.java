package items;

public class Armor extends Item {
	public Armor(String type) {
		super.setEquipable();
		super.setRestriction(new String[] {"warrior", "assassin"});
	}
}
