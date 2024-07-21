package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.Poison;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class Slime extends Enemie {

	public Slime(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new Poison(), 2);
		super.setName("GOSMA");
		
		super.setExperience(10);
		super.setGold(10);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(150);
			stats.setMaxHealth(150);
			stats.setMana(15);
			stats.setMaxMana(15);
			
			stats.setStrenght(5);
			stats.setDefense(5);
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
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/enemies/slime.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
