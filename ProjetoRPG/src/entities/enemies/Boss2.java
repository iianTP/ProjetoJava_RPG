package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;
import states.battle.spells.Hypnosis;
import states.battle.spells.Lightning;

public class Boss2 extends Enemie {

	public Boss2(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new Hypnosis(), 2);
		super.getSpells().learnSpell(new Lightning(), 3);
		super.setName("EX-MAK1_N4");
		
		super.setExperience(35);
		super.setGold(45);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(100);
			stats.setMaxHealth(100);
			stats.setMana(40);
			stats.setMaxMana(40);
			
			stats.setStrenght(10);
			stats.setDefense(2);
			stats.setMagic(5);
			stats.setMagicDefense(4);
			stats.setAgility(1);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/bosses/boss2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
