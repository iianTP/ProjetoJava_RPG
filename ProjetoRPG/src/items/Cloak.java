package items;

public class Cloak extends Item {
	
	private int defense;
	private int agility;
	
	public Cloak(int type) {
		super.isEquipable();
		super.setRestriction(new String[] {"mage", "healer"});
		
		switch(type) {
		case 1:
			super.setName("Capa Simples");
			super.setDescription("Capa ideal para iniciantes em treinamento.");
			this.defense = 1;
			break;
		case 2:
			super.setName("teste");
			this.defense = 2;
			break;
		case 3:
			super.setName("teste");
			this.defense = 3;
			break;
		case 4:
			super.setName("teste");
			this.defense = 4;
			break;
		case 5:
			super.setName("teste");
			this.defense = 5;
			break;
		case 6:
			super.setName("teste");
			this.defense = 6;
			break;
		}
	}

	public int getDefense() {
		return defense;
	}

	public int getAgility() {
		return agility;
	}
}
