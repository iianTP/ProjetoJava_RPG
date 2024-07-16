package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.Lightning;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class Ghost extends Enemie {
	
	public Ghost(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
	/*	super.getSpells().learnSpell(new FireBall(), 2);
		super.getSpells().learnSpell(new FireBall(), 3);
		super.getSpells().learnSpell(new FireBall(), 4);
		super.getSpells().learnSpell(new FireBall(), 5);*/
		super.getSpells().learnSpell(new Lightning(), 2);
		super.getSpells().learnSpell(new Lightning(), 3);
		super.getSpells().learnSpell(new Lightning(), 4);
		super.getSpells().learnSpell(new Lightning(), 5);
		super.setName("FANTASMA");
		
		super.setExperience(10);
		super.setGold(15);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(10);
			stats.setMaxHealth(10);
			stats.setMana(20);
			stats.setMaxMana(20);
			
			stats.setStrenght(10);
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
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleDown.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
