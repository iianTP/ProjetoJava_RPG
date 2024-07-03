package entities.teammates;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import entities.npcs.Npc;
import entities.player.Player;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;

public class AssassinNpc extends Teammate {
	
	private Stats stats = new Stats();
	private String name = "ASSASSINO";
	
	public AssassinNpc(int x, int y, GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		try {
			super.setX(x);
			super.setY(y);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		};
		super.setWalkSpeed(3);
		super.setDirection("down");
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
	
	public void setStats() {
		try {
			this.stats.setHealth(20);
			this.stats.setMaxHealth(20);
			this.stats.setMana(20);
			this.stats.setMaxMana(20);
			
			this.stats.setStrenght(3);
			this.stats.setDefense(5);
			this.stats.setAgility(4);
			this.stats.setCriticalDamage(20);
			this.stats.setMagic(2);
			this.stats.setMagicDefense(2);
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
