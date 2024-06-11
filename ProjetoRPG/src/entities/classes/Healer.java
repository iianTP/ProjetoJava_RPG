package entities.classes;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Player;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import main.KeyInput;
import main.screen.GameScreen;

public class Healer extends Player{
	
	private Stats stats = new Stats();

	public Healer(KeyInput key, Npc[] npcs, GameScreen gs) {
		super(key, npcs, gs);	
		this.setSprites();
		this.setStats();
	}
	
	@Override
	public void setSprites() {
		
		try {
			
			super.setIdleSprites(
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleUp.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleDown.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleLeft.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleRight.png"))
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
		
		this.stats.setStrenght(1);
		this.stats.setDefense(2);
		this.stats.setMagic(4);
		this.stats.setMagicDefense(5);
		super.setStats(stats);
	}
	
}
