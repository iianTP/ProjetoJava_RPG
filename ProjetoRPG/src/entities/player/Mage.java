package entities.player;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import main.KeyInput;
import main.screen.GameScreen;

public class Mage extends Player {
	
	private Stats stats = new Stats();

	public Mage(KeyInput key, GameScreen gs) {
		super(key, gs);
		this.setSprites();
		this.setStats();
	}
	
	@Override
	public void setSprites() {
		
		try {
			
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleUp.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleDown.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleLeft.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleRight.png"))
			);
			
			super.setWalkSprites();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setStats() {
		
		this.stats.setHealth(20);
		this.stats.setMaxHealth(20);
		this.stats.setMana(20);
		this.stats.setMaxMana(20);
		
		this.stats.setStrenght(2);
		this.stats.setDefense(3);
		this.stats.setAgility(3);
		this.stats.setCriticalDamage(15);
		this.stats.setMagic(5);
		this.stats.setMagicDefense(4);
		super.setStats(stats);
	}
	
}
