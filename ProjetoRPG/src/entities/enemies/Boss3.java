package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;
import states.battle.spells.HealthSteal;
import states.battle.spells.Lightning;
import states.battle.spells.Poison;
import states.battle.spells.WindBlades;

public class Boss3 extends Enemie{

	public Boss3(GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.getSpells().learnSpell(new HealthSteal(), 2);
		super.getSpells().learnSpell(new WindBlades(), 3);
		super.getSpells().learnSpell(new Poison(), 4);
		super.setName("AM-TERRA");
		
		super.setExperience(40);
		super.setGold(60);
		
	}
	
	public void setStats() {
		
		try {
			Stats stats = new Stats();
			stats.setHealth(125);
			stats.setMaxHealth(125);
			stats.setMana(50);
			stats.setMaxMana(50);
			
			stats.setStrenght(10);
			stats.setDefense(3);
			stats.setMagic(3);
			stats.setMagicDefense(4);
			stats.setAgility(4);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/bosses/boss3.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
