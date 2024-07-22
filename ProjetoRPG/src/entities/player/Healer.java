package entities.player;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import entities.teammates.Team;
import exceptions.InvalidStatsInputException;
import main.KeyInput;
import main.screen.GameScreen;
import states.battle.Battle;

public class Healer extends Player{
	
	private Stats stats = new Stats();

	public Healer(KeyInput key, GameScreen gs) {
		super(key, gs);	
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
			super.setWalkSprites(
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkUp1.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkUp2.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkDown1.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkDown2.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkLeft1.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkLeft2.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkRight1.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerWalkRight2.png"))
			);
					
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
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
	public <T> void special(T target, Battle battle) {
		
		if (target instanceof Team[]) {
			for (int i = 0; i < 4; i++) {
				if (!(((Team[])target)[i] instanceof Healer)) {
					try {
						((Team[])target)[i].getStats().heal(10);
					} catch (InvalidStatsInputException e) {
						e.printStackTrace();
					}
				}
			}
			battle.setMessage("VOCE CUROU SEUS ALIADOS (+10HP)");
			super.getStats().resetOverdrive();
		}
		
	}
	
}
