package items;

public class Staff extends Item {
	
	private int strength;
	private int magic;
	
	public Staff(int type) {
		super.setEquipable();
		super.setRestriction(new String[] {"mage", "healer"});
		
		switch(type) {
		case 1:
			super.setName("Bastao Magico");
			super.setShortName("B.MAGIC.");
			super.setDescription("Bastao utilizado para treinar magos iniciantes.");
			this.strength = 1;
			this.magic = 1;
			break;
		case 2:
			super.setName("Cajado avancado");
			super.setShortName("C.AVANC.");
			super.setDescription("Cajado comum entre magos experientes.");
			this.strength = 1;
			this.magic = 2;
			break;
		case 3:
			super.setName("Cajado de Cinzas");
			super.setShortName("C.CINZ.");
			super.setDescription("Cajado feito a partir dos restos dos Ents incinerados pelo exercito de Maglorg.");
			this.strength = 2;
			this.magic = 3;
			break;
		case 4:
			super.setName("Cajado Metalico");
			super.setShortName("C.METAL.");
			super.setDescription("Cajado experimental forjado com um pouco de todos os metais conhecidos.");
			this.strength = 2;
			this.magic = 4;
			break;
		case 5:
			super.setName("Mae Terra");
			super.setShortName("M.TERR.");
			super.setDescription("Cajado feito a partir das raizes da arvore mais antiga.");
			this.strength = 3;
			this.magic = 5;
			break;
		case 6:
			super.setName("Eclydnpyopyetl");
			super.setShortName("ECLYDN.");
			super.setDescription("Misterioso cajado feito por uma sociedade esquecida.");
			this.strength = 3;
			this.magic = 6;
			break;
		}
		
	}
	
	@Override
	public String getProperties() {
		return "ATQ: +"+this.strength+" MGC: +"+this.magic;
	}


	public int getStrength() {
		return strength;
	}

	public int getMagic() {
		return magic;
	}

}