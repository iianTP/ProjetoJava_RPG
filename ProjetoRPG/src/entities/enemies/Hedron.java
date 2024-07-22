package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;
import states.battle.spells.FireBall;
import states.battle.spells.Lightning;

public class Hedron extends Enemie {
	
	public Hedron(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new Lightning(), 2);
		super.getSpells().learnSpell(new FireBall(), 3);
		super.setName("EDRO");
		
		super.setExperience(8);
		super.setGold(12);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(70);
			stats.setMaxHealth(70);
			stats.setMana(30);
			stats.setMaxMana(30);
			
			stats.setStrenght(7);
			stats.setDefense(4);
			stats.setMagic(4);
			stats.setMagicDefense(2);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/enemies/hedron.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
