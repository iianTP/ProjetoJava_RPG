package items;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Cloak extends Item {
	
	private int magicDefense;
	private int agility;
	
	public Cloak(int type) {
		super.setEquipable();
		super.setRestriction(new String[] {"mage", "healer"});
		
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/items/cloak_"+(type-1)+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		super.setType(type);
		
		switch(type) {
		case 1:
			super.setName("Capa Simples");
			super.setShortName("C.SIMPL.");
			super.setDescription("Capa feita para magos iniciantes.");
			super.setSellPrice(5);
			this.magicDefense = 1;
			this.agility = 1;
			break;
		case 2:
			super.setName("Capa Vermelha");
			super.setShortName("C.VERME.");
			super.setDescription("Capa feita a partir das escamas de um dragao anciao.");
			super.setSellPrice(10);
			this.magicDefense = 2;
			this.agility = 1;
			break;
		case 3:
			super.setName("Manto do Ladrao");
			super.setShortName("M.LADRA.");
			super.setDescription("Manto ideal para agir furtivamente.");
			super.setSellPrice(15);
			this.magicDefense = 3;
			this.agility = 2;
			break;
		case 4:
			super.setName("Alto Conselho");
			super.setShortName("A.CONSE.");
			super.setDescription("Manto dos lideres do Conselho dos Magos.");
			super.setSellPrice(20);
			this.magicDefense = 4;
			this.agility = 2;
			break;
		case 5:
			super.setName("Fiel Mensageiro");
			super.setShortName("F.MENSA.");
			super.setDescription("Manto dos fieis mensageiros da realeza do mundo 3.");
			super.setSellPrice(25);
			this.magicDefense = 5;
			this.agility = 3;
			break;
		case 6:
			super.setName("Arte das Trevas");
			super.setShortName("A.TREVA.");
			super.setDescription("Manto de origem desconhecida, considerado amaldicoado por muitos.");
			super.setSellPrice(30);
			this.magicDefense = 6;
			this.agility = 3;
			break;
		}
	}
	
	@Override
	public String getProperties() {
		return "MGD: +"+this.magicDefense+" AGL: +"+this.agility;
	}

	public int getDefense() {
		return magicDefense;
	}

	public int getAgility() {
		return agility;
	}
}
