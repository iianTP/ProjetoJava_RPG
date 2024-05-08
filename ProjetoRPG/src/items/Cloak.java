package items;

public class Cloak extends Item {
	public Cloak() {
		super.isEquipable();
		super.setRestriction(new String[] {"mage", "healer"});
	}
}
