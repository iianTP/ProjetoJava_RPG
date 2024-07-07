package items;

import combat.spells.*;

public class Book extends Item {
	
	private int type;
	private Spell spell;
	
	public Book(int type) {
		super.setConsumable();
		
		this.type = type;
		
		switch(type) {
		case 1:
			super.setName("Aprenda Disparo de Energia");
			this.spell = new EnergyBullet();
			break;
		case 2:
			super.setName("Aprenda Bola de Fogo");
			this.spell = new FireBall();
			break;
		case 3:
			super.setName("Aprenda Hipnose");
			this.spell = new Hypnosis();
			break;
		case 4:
			super.setName("Aprenda Maldicao");
			this.spell = new Curse();
			break;
		case 5:
			super.setName("Aprenda Roubo de Vida");
			this.spell = new HealthSteal();
			break;
		case 6:
			super.setName("Aprenda Veneno");
			this.spell = new Poison();
			break;
		case 7:
			super.setName("Aprenda Laminas de Vento");
			this.spell = new WindBlades();
			break;
		case 8:
			super.setName("Aprenda Relampago");
			this.spell = new Lightning();
			break;
		case 9:
			super.setName("Aprenda Lanca de Sangue");
			this.spell = new BloodSpear();
			break;
		case 10:
			super.setName("Aprenda Magia Sombria");
			this.spell = new DarkMagic();
			break;
		}
	}
	
	public void readBook(KnownSpells spells, int slot) {
		spells.learnSpell(spell, slot);
	}
}
