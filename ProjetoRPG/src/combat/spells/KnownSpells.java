package combat.spells;

import entities.Battler;
import entities.Stats;
import entities.enemies.Enemie;
import exceptions.InvalidSpellIdException;
import states.Battle;

public class KnownSpells {
	
	private Spell spell1 = new EnergyBullet();
	private Spell spell2 = new Lightning();
	private Spell spell3;
	private Spell spell4;
	private Spell spell5;
	
	private Stats stats;
	
	public KnownSpells(Stats stats) {
		this.stats = stats;
	}
	
	public void castSpell(int spellId, Battler target, Battle battle) throws InvalidSpellIdException {
		if (spellId < 0 || spellId > 4) {
			throw new InvalidSpellIdException("ID do feitiço "+spellId+" inválido");
		}
		switch(spellId) {
		case 0:
			System.out.println("first");
			if (spell1 != null) spell1.castSpell(target, this.stats, battle);
			break;
		case 1:
			if (spell2 != null) spell2.castSpell(target, this.stats, battle);
			break;
		case 2:
			if (spell3 != null) spell3.castSpell(target, this.stats, battle);
			break;
		case 3:
			if (spell4 != null) spell4.castSpell(target, this.stats, battle);
			break;
		case 4:
			if (spell5 != null) spell5.castSpell(target, this.stats, battle);
			break;
		}
		
	}
	
	public void learnSpell(Spell spell, int slot) {
		if (slot < 1 || slot > 5) {
			
		}
		switch(slot) {
		case 1:
			this.spell1 = spell;
			break;
		case 2:
			this.spell2 = spell;
			break;
		case 3:
			this.spell3 = spell;
			break;
		case 4:
			this.spell4 = spell;
			break;
		case 5:
			this.spell5 = spell;
			break;
		}
	}
	
	public Spell getSpell(int id) {
		Spell spell = null;
		switch (id) {
		case 1:
			spell = this.spell1;
			break;
		case 2:
			spell = this.spell2;
			break;
		case 3:
			spell = this.spell3;
			break;
		case 4:
			spell = this.spell4;
			break;
		case 5:
			spell = this.spell5;
			break;
		}
		return spell;
	}
	
	public void resetDarkMagic() {
		for (int i = 1; i < 6; i++) {
			Spell spell = this.getSpell(i);
			if (spell instanceof DarkMagic) {
				((DarkMagic) spell).resetDamage();
			}
		}
	}
	

}
