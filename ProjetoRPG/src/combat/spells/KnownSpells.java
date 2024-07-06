package combat.spells;

import entities.Stats;
import entities.enemies.Enemie;
import exceptions.InvalidSpellIdException;
import combat.spells.*;

public class KnownSpells {
	
	private Spell spell1 = new BloodSpear();
	private Spell spell2 = new FireBall();
	private Spell spell3 = new Curse();
	private Spell spell4 = new Poison();
	private Spell spell5 = new Lightning();
	
	private Stats stats;
	
	private int freeSlot = 1;
	
	public KnownSpells(Stats stats) {
		this.stats = stats;
	}
	
	public void castSpell(int spellId, Enemie target) throws InvalidSpellIdException {
		if (spellId < 0 || spellId > 4) {
			throw new InvalidSpellIdException("ID do feitiço "+spellId+" inválido");
		}
		switch(spellId) {
		case 0:
			if (spell1 != null) spell1.castSpell(target, this.stats);
			break;
		case 1:
			if (spell2 != null) spell2.castSpell(target, this.stats);
			break;
		case 2:
			if (spell3 != null) spell3.castSpell(target, this.stats);
			break;
		case 3:
			if (spell4 != null) spell4.castSpell(target, this.stats);
			break;
		case 4:
			if (spell5 != null) spell5.castSpell(target, this.stats);
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
		if (this.freeSlot <= 5) {
			this.freeSlot++;
		}
	}
	
	public int getFreeSlot() {
		return this.freeSlot;
	}
	
	public Spell getSpell1() {
		return this.spell1;
	}
	public Spell getSpell2() {
		return this.spell2;
	}
	public Spell getSpell3() {
		return this.spell3;
	}
	public Spell getSpell4() {
		return this.spell4;
	}
	public Spell getSpell5() {
		return this.spell5;
	}
	

}
