package entities.classes;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Player;
import entities.Stats;
import entities.npcs.Npc;
import main.KeyInput;
import main.screen.GameScreen;

public class Assassin extends Player {
	
	private Stats stats = new Stats();
	
	public Assassin(KeyInput key, Npc[] npcs, GameScreen gs) {
		super(key, npcs, gs);
		this.setSprites();
		this.setStats();
	}
	
	@Override
	public void setSprites() {
		
		try {
			
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleUp.png")),
				ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleDown.png")),
				ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleLeft.png")),
				ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleRight.png"))
			);
			
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
		
		this.stats.setStrenght(3);
		this.stats.setDefense(5);
		this.stats.setMagic(2);
		this.stats.setMagicDefense(2);
		super.setStats(stats);
	}

}
