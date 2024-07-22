package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;
import states.battle.spells.BloodSpear;
import states.battle.spells.FireBall;

public class Boss1 extends Enemie {

	public Boss1(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new FireBall(), 2);
		super.getSpells().learnSpell(new BloodSpear(), 3);
		super.setName("MAGLORG");
		
		super.setExperience(10);
		super.setGold(15);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(75);
			stats.setMaxHealth(75);
			stats.setMana(30);
			stats.setMaxMana(30);
			
			stats.setStrenght(10);
			stats.setDefense(5);
			stats.setMagic(2);
			stats.setMagicDefense(1);
			stats.setAgility(2);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/bosses/boss1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
