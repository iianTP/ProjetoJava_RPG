package entities.player;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Battler;
import entities.Stats;
import entities.enemies.Enemie;
import entities.teammates.Team;
import exceptions.InvalidStatsInputException;
import main.KeyInput;
import main.screen.GameScreen;
import states.Battle;

public class Mage extends Player {
	
	private Stats stats = new Stats();

	public Mage(KeyInput key, GameScreen gs) {
		super(key, gs);
		this.setSprites();
		this.setStats();
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
			
			super.setWalkSprites(
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkUp1.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkUp2.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkDown1.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkDown2.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkLeft1.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkLeft2.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkRight1.png")),
				ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkRight2.png"))
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

	@Override
	public <T> void special(T target, Battle battle) {
		
		if (target instanceof Battler[]) {
			
			Battler[] battlers = (Battler[]) target;
			
			for (int i = 0; i < 5; i++) {
				
				if (battlers[i] instanceof Enemie) {
					
					((Enemie) battlers[i]).getEffects().setCurrentEffect("hypnotized");
					
				} else if ( !(battlers[i] instanceof Mage) ) {
					
					((Team) battlers[i]).getStats().setSpecialMagicDefense(5);
					
				}
				
			}
			battle.setMessage("VOCE DEIXOU O OPONENTE HIPNOTIZADO E AUMENTOU TEMPORARIAMENTE A DEFESA MAGICA DO TIME (+5MGD)");
			super.getStats().resetOverdrive();
		}
		
	}
	
}
