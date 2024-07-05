package items;

public class Staff extends Item {
	
	private int strength;
	private int magic;
	
	public Staff(int type) {
		super.setEquipable();
		super.setRestriction(new String[] {"mage", "healer"});
		
		switch(type) {
		case 1:
			super.setName("Capa Simples");
			super.setDescription("Capa ideal para iniciantes em treinamento.");
			this.strength = 1;
			break;
		case 2:
			super.setName("teste");
			this.strength = 1;
			break;
		case 3:
			super.setName("teste");
			this.strength = 2;
			break;
		case 4:
			super.setName("teste");
			this.strength = 2;
			break;
		case 5:
			super.setName("teste");
			this.strength = 3;
			break;
		case 6:
			super.setName("teste");
			this.strength = 3;
			break;
		}
		
	}

	public int getStrength() {
		return strength;
	}

	public int getMagic() {
		return magic;
	}

}