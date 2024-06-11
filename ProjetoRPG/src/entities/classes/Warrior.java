package entities.classes;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Player;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import main.KeyInput;
import main.screen.GameScreen;

public class Warrior extends Player {
	
	private Stats stats = new Stats();

	public Warrior(KeyInput key, Npc[] npcs, GameScreen gs) {
		super(key, npcs, gs);
		this.setSprites();
		this.setStats();
	}
	
	@Override
	public void setSprites() {
	
		try {
			
			super.setIdleSprites(
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleUp.png")),
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleDown.png")),
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleLeft.png")),
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleRight.png"))
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
		
		this.stats.setStrenght(5);
		this.stats.setDefense(4);
		this.stats.setMagic(1);
		this.stats.setMagicDefense(1);
		super.setStats(stats);
	}

}
