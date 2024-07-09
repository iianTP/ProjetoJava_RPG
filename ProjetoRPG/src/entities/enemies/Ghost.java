package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.FireBall;
import combat.spells.Lightning;
import entities.Entity;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class Ghost extends Enemie {
	
	public Ghost(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new FireBall(), 2);
		super.getSpells().learnSpell(new FireBall(), 3);
		super.getSpells().learnSpell(new FireBall(), 4);
		super.getSpells().learnSpell(new FireBall(), 5);
		//super.getSpells().learnSpell(new Lightning(), 3);
		super.setName("FANTASMA");
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(20);
			stats.setMaxHealth(20);
			stats.setMana(20);
			stats.setMaxMana(20);
			
			stats.setStrenght(3);
			stats.setDefense(5);
			stats.setMagic(2);
			stats.setMagicDefense(1);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public <T> void special(T target) {
		// TODO Auto-generated method stub
		
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
