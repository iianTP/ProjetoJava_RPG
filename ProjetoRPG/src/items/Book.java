package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import states.battle.spells.*;

public class Book extends Item {
	
	private Spell spell;
	
	public Book(int type) {
		
		super.setConsumable();
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/items/book_0"+(type-1)+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		super.setType(type);
		
		super.setDescription("Um livro de magia.");
		
		switch(type) {
		case 1:
			super.setName("Disparo de Energia");
			this.spell = new EnergyBullet();
			super.setSellPrice(3);
			break;
		case 2:
			super.setName("Bola de Fogo");
			this.spell = new FireBall();
			super.setSellPrice(5);
			break;
		case 3:
			super.setName("Hipnose");
			this.spell = new Hypnosis();
			super.setSellPrice(10);
			break;
		case 4:
			super.setName("Maldicao");
			this.spell = new Curse();
			super.setSellPrice(13);
			break;
		case 5:
			super.setName("Roubo de Vida");
			this.spell = new HealthSteal();
			super.setSellPrice(10);
			break;
		case 6:
			super.setName("Veneno");
			this.spell = new Poison();
			super.setSellPrice(13);
			break;
		case 7:
			super.setName("Laminas de Vento");
			this.spell = new WindBlades();
			super.setSellPrice(20);
			break;
		case 8:
			super.setName("Relampago");
			this.spell = new Lightning();
			super.setSellPrice(30);
			break;
		case 9:
			super.setName("Lanca de Sangue");
			this.spell = new BloodSpear();
			super.setSellPrice(40);
			break;
		case 10:
			super.setName("Magia Sombria");
			this.spell = new DarkMagic();
			super.setSellPrice(50);
			break;
		}
	}
	
	@Override
	public String getProperties() {
		return "APRENDA "+spell.getShortSpellName().toUpperCase();
	}
	
	public void readBook(KnownSpells spells, int slot) {
		spells.learnSpell(spell, slot);
	}

}