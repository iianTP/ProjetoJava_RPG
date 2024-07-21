package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.FireBall;
import combat.spells.Lightning;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class Sentinel extends Enemie {

	public Sentinel(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new FireBall(), 2);
		super.setName("SENTINELA");
		
		super.setExperience(11);
		super.setGold(17);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(100);
			stats.setMaxHealth(100);
			stats.setMana(20);
			stats.setMaxMana(20);
			
			stats.setStrenght(13);
			stats.setDefense(3);
			stats.setMagic(1);
			stats.setMagicDefense(3);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/enemies/sentinel.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
