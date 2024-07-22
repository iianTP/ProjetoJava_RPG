package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.*;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class Ghost extends Enemie {
	
	public Ghost(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new Curse(), 2);
		super.getSpells().learnSpell(new Hypnosis(), 3);
		super.getSpells().learnSpell(new DarkMagic(), 4);
		super.setName("FANTASMA");
		
		super.setExperience(10);
		super.setGold(15);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(100);
			stats.setMaxHealth(100);
			stats.setMana(50);
			stats.setMaxMana(50);
			
			stats.setStrenght(7);
			stats.setDefense(5);
			stats.setMagic(2);
			stats.setMagicDefense(1);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/enemies/ghost.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
