package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import combat.spells.FireBall;
import combat.spells.Lightning;
import combat.spells.WindBlades;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class Wizard extends Enemie {

	public Wizard(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new WindBlades(), 2);
		super.getSpells().learnSpell(new FireBall(), 3);
		super.getSpells().learnSpell(new Lightning(), 4);
		super.setName("BRUXO");
		
		super.setExperience(10);
		super.setGold(15);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(80);
			stats.setMaxHealth(80);
			stats.setMana(60);
			stats.setMaxMana(60);
			
			stats.setStrenght(5);
			stats.setDefense(2);
			stats.setMagic(5);
			stats.setMagicDefense(4);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/enemies/wizard.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
