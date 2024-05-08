package items;

public class Staff extends Item {
	public Staff() {
		super.setEquipable();
		super.setRestriction(new String[] {"mage", "healer"});
	}
}
