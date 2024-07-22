package entities.teammates;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import entities.player.Healer;
import exceptions.InvalidStatsInputException;
import main.screen.GameScreen;
import states.battle.Battle;

public class HealerNpc extends Teammate {
	
	private Stats stats = new Stats();

	public HealerNpc(int x, int y, GameScreen gs) {
		super(gs);	
		this.setStats();
		this.setSprites();
		
		super.setX(x);
		super.setY(y);
			
		super.setWalkSpeed(3);
		super.setDirection("down");
		super.setName("CURANDEIRO");
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
			battle.setMessage("O CURANDEIRO CUROU OS ALIADOS (+10HP)");
			super.getStats().resetOverdrive();
		}
	}

}
