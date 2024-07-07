package entities.enemies;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Entity;
import entities.Stats;
import exceptions.InvalidStatsInputException;

public class Ghost extends Enemie {
	
	private Stats stats = new Stats();
	
	public Ghost() {
		this.setSprite();
		this.setStats();
		super.setStats(stats);
	}
	
	public void setStats() {
		
		try {
			this.stats.setHealth(200);
			this.stats.setMaxHealth(200);
			this.stats.setMana(20);
			this.stats.setMaxMana(20);
			
			this.stats.setStrenght(3);
			this.stats.setDefense(5);
			this.stats.setMagic(2);
			this.stats.setMagicDefense(1);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setSprite() {
		
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleDown.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public <T> void special(T target) {
		// TODO Auto-generated method stub
		
	}

}
