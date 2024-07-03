package entities.teammates;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import entities.player.Player;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidStatsInputException;
import main.KeyInput;
import main.screen.GameScreen;

public class HealerNpc extends Teammate {
	
	private Stats stats = new Stats();
	private String name = "CURANDEIRO";

	public HealerNpc(int x, int y, GameScreen gs) {
		super(gs);	
		this.setStats();
		this.setSprites();
		try {
			super.setX(x);
			super.setY(y);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}
		super.setWalkSpeed(3);
		super.setDirection("down");
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
	

	public void setStats() {
		
		try {
			this.stats.setHealth(20);
			this.stats.setMaxHealth(20);
			this.stats.setMana(20);
			this.stats.setMaxMana(20);
			
			this.stats.setStrenght(1);
			this.stats.setDefense(2);
			this.stats.setAgility(2);
			this.stats.setCriticalDamage(10);
			this.stats.setMagic(4);
			this.stats.setMagicDefense(5);
			super.setStats(stats);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}

	}


	@Override
	public <T> void special(T target) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		return name;
	}
	
}
