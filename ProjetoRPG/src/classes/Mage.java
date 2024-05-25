package classes;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Npc;
import entities.Player;
import entities.Stats;
import main.GameScreen;
import main.KeyInput;

public class Mage extends Player {
	
	private Stats stats = new Stats();

	public Mage(KeyInput key, Npc[] npcs, GameScreen gs) {
		super(key, npcs, gs);
		this.setSprites();
		this.setStats();
		super.setStats(stats);
	}
	
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
	
	public void setStats() {
		
		this.stats.setHealth(20);
		this.stats.setMaxHealth(20);
		this.stats.setMana(20);
		this.stats.setMaxMana(20);
		
		this.stats.setStrenght(2);
		this.stats.setDefense(3);
		this.stats.setMagic(5);
		this.stats.setMagicDefense(4);

	}
	

}
