package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Sword extends Item {
	
	private int strength;
	private int agility;
	
	public Sword(int type) {
		super.setEquipable();
		super.setRestriction(new String[] {"warrior"});
		
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/items/sword_"+(type-1)+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(type) {
		case 1:
			super.setName("Espada Basica");
			super.setShortName("E.BASIC.");
			super.setDescription("Espada comum utilizada por cavaleiros.");
			this.strength = 1;
			this.agility = 1;
			break;
		case 2:
			super.setName("Espada Dupla");
			super.setShortName("B.DUPLA");
			super.setDescription("Duas espadas basicas.");
			this.strength = 2;
			this.agility = 1;
			break;
		case 3:
			super.setName("Honra de Mosqueteiro");
			super.setShortName("H.MOSQU.");
			super.setDescription("Espada fina, leve e veloz.");
			this.strength = 3;
			this.agility = 2;
			break;
		case 4:
			super.setName("Katana");
			super.setShortName("KATAN.");
			super.setDescription("Espada rapida bastante afiada.");
			this.strength = 4;
			this.agility = 2;
			break;
		case 5:
			super.setName("A Uniao");
			super.setShortName("UNIAO");
			super.setDescription("Espada forjada pelos 3 maiores ferreiros dos 3 mundos.");
			this.strength = 5;
			this.agility = 3;
			break;
		case 6:
			super.setName("Espada das Montanhas");
			super.setShortName("E.MONTA.");
			super.setDescription("Espada afiada pelos fortes ventos do mundo 3.");
			this.strength = 6;
			this.agility = 3;
			break;
		}
		
	}
	
	@Override
	public String getProperties() {
		return "ATQ: +"+this.strength+" AGL: +"+this.agility;
	}

	public int getStrength() {
		return strength;
	}

	public int getAgility() {
		return agility;
	}
}
