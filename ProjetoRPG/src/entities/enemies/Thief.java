package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.BloodSpear;
import combat.spells.HealthSteal;
import combat.spells.Poison;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class Thief extends Enemie {

	public Thief(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new HealthSteal(), 2);
		super.getSpells().learnSpell(new BloodSpear(), 3);
		super.getSpells().learnSpell(new Poison(), 4);
		super.setName("LADRAO");
		
		super.setExperience(10);
		super.setGold(20);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(30);
			stats.setMaxHealth(30);
			stats.setMana(35);
			stats.setMaxMana(35);
			
			stats.setStrenght(6);
			stats.setDefense(3);
			stats.setMagic(3);
			stats.setMagicDefense(2);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/enemies/thief.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
