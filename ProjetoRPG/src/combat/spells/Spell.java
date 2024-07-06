package combat.spells;

import interfaces.ISpell;

public abstract class Spell implements ISpell {
	
	private String spellName;
	private String shortSpellName;
	private int manaCost;
	
	public String getSpellName() {
		return spellName;
	}
	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}
	public String getShortSpellName() {
		return shortSpellName;
	}
	public void setShortSpellName(String shortSpellName) {
		this.shortSpellName = shortSpellName;
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
	

}