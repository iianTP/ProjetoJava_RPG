package combat;

import java.util.Arrays;
import java.util.Random;

import combat.spells.KnownSpells;

public class BattleRng {
	
	private int attackChance = 10;
	private int defenseChance = 5;
	private int magicChance = 10;
	private int specialChance = 5;
	
	private final Random random;
	
	public BattleRng(Random random) {
		this.random = random;
	}
	
	public int rng(int range, int minNum) {
		return this.random.nextInt(range) + minNum;
	}
	
	public String chooseMove(int overdrive) {
		
		int fullChance = defenseChance+attackChance+magicChance;
		
		fullChance += (overdrive == 100) ? specialChance : 0;
		
		int move = this.rng(fullChance, 1);	
		
		if (move <= attackChance) {
			return "attack";
		} else if (move <= attackChance+defenseChance) {
			return "defense";
		} else if (move <= attackChance+defenseChance+magicChance) {
			return "magic";
		} else {
			return "special";
		}
	}
	
	public int getRandomSpellId(KnownSpells spells, int mana) {
		
		int[] spellIds= new int[0];
		for (int i = 1; i < 6; i++) {
			if (spells.getSpell(i) != null && -1*spells.getSpell(i).getManaCost() <= mana) {
				spellIds = Arrays.copyOf(spellIds, spellIds.length+1);
				spellIds[spellIds.length-1] = i;
			}
		}
		
		return (spellIds.length != 0) ? spellIds[this.rng(spellIds.length, 0)] : -1;
		
	}
	
	public int getAttackChance() {
		return attackChance;
	}
	public void increaseAttackChance() {
		this.attackChance += 2;
	}
	public void increaseDefenseChance() {
		this.defenseChance += 1;
	}
	
	public int getFullChance() {
		return attackChance + defenseChance;
	}

}
