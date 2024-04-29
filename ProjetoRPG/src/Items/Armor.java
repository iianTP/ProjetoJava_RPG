package Items;

public class Armor extends Item {
	public Armor() {
		super.setEquipable();
		super.setRestriction(new String[] {"warrior", "assassin"});
	}
}
