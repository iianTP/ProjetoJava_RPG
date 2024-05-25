package enemies;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Enemie;
import entities.Stats;

public class Ghost extends Enemie {
	
	private Stats stats = new Stats();
	
	public Ghost() {
		this.setSprite();
		this.setStats();
		super.setStats(stats);
	}
	
	public void setStats() {
		
		this.stats.setHealth(20);
		this.stats.setMaxHealth(20);
		this.stats.setMana(20);
		this.stats.setMaxMana(20);
		
		this.stats.setStrenght(3);
		this.stats.setDefense(5);
		this.stats.setMagic(2);
		this.stats.setMagicDefense(1);

	}
	
	public void setSprite() {
		
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleDown.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
