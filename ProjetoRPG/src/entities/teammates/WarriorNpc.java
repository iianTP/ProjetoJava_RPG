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
import states.Battle;

public class WarriorNpc extends Teammate {
	
	private Stats stats = new Stats();

	public WarriorNpc(int x, int y, GameScreen gs) {
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
		super.setName("GUERREIRO");
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
		
		try {
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
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}

	}

	public <T> void special(T target, Battle battle) {
		if (target instanceof Enemie) {
			int damage = 2*super.getStats().getStrength();
			((Enemie)target).takeDamage(damage);
			
			battle.setMessage("O GUERREIRO ATACOU O OPONENTE COM TODA FORCA E VELOCIDADE (-"+damage+"HP)");
			super.getStats().resetOverdrive();
		}
	}


}
