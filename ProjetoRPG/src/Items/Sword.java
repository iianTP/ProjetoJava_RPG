package Items;

public class Sword extends Item {
	public Sword() {
		super.setEquipable();
		super.setRestriction(new String[] {"warrior"});
	}
}
