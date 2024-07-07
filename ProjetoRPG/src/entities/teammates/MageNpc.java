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

public class MageNpc extends Teammate {
	
	private Stats stats = new Stats();

	public MageNpc(int x, int y, GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		try {
			super.setX(x);
			super.setY(y);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}
		super.setWalkSpeed(3);
		super.setDirection("down");
		super.setName("MAGO");
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
	
	public void setStats() {
		
		try {
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
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}

	}

	public <T> void special(T target) {
		// TODO Auto-generated method stub
		
	}

	
}
