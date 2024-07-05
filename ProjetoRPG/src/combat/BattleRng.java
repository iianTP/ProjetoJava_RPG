package combat;

import java.util.Random;

public class BattleRng {
	//ashdbjwefhbjef
	private int attackChance = 10;
	private int defenseChance = 5;
	
	private final Random random = new Random();
	
	public int rng(int range, int minNum) {
		return this.random.nextInt(range) + minNum;
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
