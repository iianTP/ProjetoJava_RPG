package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Armor extends Item {
	
	private int defense;
	private int agility;
	
	public Armor(int type) {
		super.setEquipable();
		super.setRestriction(new String[] {"warrior", "assassin"});
		
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/items/armor_"+(type-1)+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(type) {
		case 1:
			super.setName("Armadura Simples");
			super.setShortName("A.SIMPL.");
			super.setDescription("Armadura ideal para iniciantes em treinamento.");
			this.defense = 1;
			this.agility = 0;
			break;
		case 2:
			super.setName("Armadura Basica");
			super.setShortName("A.BASIC.");
			super.setDescription("Armadura mais comum nos campos de batalha.");
			this.defense = 2;
			this.agility = -1;
			break;
		case 3:
			super.setName("Armadura Pesada");
			super.setShortName("A,PESAD.");
			super.setDescription("Armadura para os mais experientes em combate.");
			this.defense = 3;
			this.agility = -2;
			break;
		case 4:
			super.setName("MK-1096");
			super.setShortName("M.1096");
			super.setDescription("Prototipo de armadura desenvolvido por cientistas do mundo 2.");
			this.defense = 4;
			this.agility = -2;
			break;
		case 5:
			super.setName("Bencao de Platina");
			super.setShortName("B.PLATI.");
			super.setDescription("Armadura considerada abencoada por lutadores dos 3 mundos.");
			this.defense = 5;
			this.agility = -2;
			break;
		case 6:
			super.setName("Gloria de Maglorg");
			super.setShortName("G.MAGLO.");
			super.setDescription("Armadura utilizada pelos mais renomados guerreiros de Maglorg.");
			this.defense = 6;
			this.agility = -3;
			break;
		}
		
	}
	
	@Override
	public String getProperties() {
		return "DEF: +"+this.defense+" AGL: "+this.agility;
	}

	public int getDefense() {
		return defense;
	}
	public int getAgility() {
		return this.agility;
	}
}
