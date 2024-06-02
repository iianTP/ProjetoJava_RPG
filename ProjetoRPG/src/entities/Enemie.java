package entities;

import java.awt.image.BufferedImage;

public class Enemie {
	
	private Stats stats;
	private BufferedImage sprite;
	
	
	
	
	
	public Stats getStats() {
		return this.stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
	}
	public BufferedImage getSprite() {
		return this.sprite;
	}
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public void damage(int damage) {
		this.stats.damage(damage);
	}
	
	

}
