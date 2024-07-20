package items;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.*;

public class Book extends Item {
	
	private int type;
	private Spell spell;
	
	public Book(int type) {
		super.setConsumable();
		
		this.type = type;
		
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/items/book_0"+(type-1)+".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		switch(type) {
		case 1:
			super.setName("Disparo de Energia");
			this.spell = new EnergyBullet();
			break;
		case 2:
			super.setName("Bola de Fogo");
			this.spell = new FireBall();
			break;
		case 3:
			super.setName("Hipnose");
			this.spell = new Hypnosis();
			break;
		case 4:
			super.setName("Maldicao");
			this.spell = new Curse();
			break;
		case 5:
			super.setName("Roubo de Vida");
			this.spell = new HealthSteal();
			break;
		case 6:
			super.setName("Veneno");
			this.spell = new Poison();
			break;
		case 7:
			super.setName("Laminas de Vento");
			this.spell = new WindBlades();
			break;
		case 8:
			super.setName("Relampago");
			this.spell = new Lightning();
			break;
		case 9:
			super.setName("Lanca de Sangue");
			this.spell = new BloodSpear();
			break;
		case 10:
			super.setName("Magia Sombria");
			this.spell = new DarkMagic();
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
