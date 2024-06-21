package items;

public class Armor extends Item {
	
	private int defense;
	
	public Armor(int type) {
		super.setEquipable();
		super.setRestriction(new String[] {"warrior", "assassin"});
		
		switch(type) {
		case 1:
			super.setName("Armadura Simples");
			super.setDescription("Armadura ideal para iniciantes em treinamento.");
			this.defense = 2;
			break;
		case 2:
			super.setName("teste");
			break;
		case 3:
			super.setName("teste");
			break;
		case 4:
			super.setName("teste");
			break;
		case 5:
			super.setName("teste");
			break;
		case 6:
			super.setName("teste");
			break;
		}
		
		
	}

	public int getDefense() {
		return defense;
	}
}
