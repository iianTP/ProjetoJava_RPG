package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Player;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import main.KeyInput;
import main.screen.GameScreen;

public class HealerNpc extends Npc {
	
	private Stats stats = new Stats();

	public HealerNpc(int x, int y, GameScreen gs) {
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
