package entities.npcs.teammates;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import entities.player.Player;
import main.KeyInput;
import main.screen.GameScreen;

public class WarriorNpc extends Teammate {
	
	private Stats stats = new Stats();

	public WarriorNpc(int x, int y, GameScreen gs) {
		super(gs);
		this.setSprites();
		this.setStats();
		super.setX(x);
		super.setY(y);
		super.setWalkSpeed(3);
		super.setDirection("down");
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
	
	public void setStats() {
		
		this.stats.setHealth(20);
		this.stats.setMaxHealth(20);
		this.stats.setMana(20);
		this.stats.setMaxMana(20);
		
		this.stats.setStrenght(5);
		this.stats.setDefense(4);
		this.stats.setAgility(3);
		this.stats.setCriticalDamage(25);
		this.stats.setMagic(1);
		this.stats.setMagicDefense(1);
		super.setStats(stats);

	}

	@Override
	public void interaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action(Player player, Npc[] npcs) {
		// TODO Auto-generated method stub
		
	}

}
