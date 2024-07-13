package entities.player;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Battler;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import exceptions.InvalidStatsInputException;
import main.KeyInput;
import main.screen.GameScreen;
import states.Battle;

public class Warrior extends Player {
	
	private Stats stats = new Stats();

	public Warrior(KeyInput key, GameScreen gs) {
		super(key, gs);
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

	@Override
	public <T> void special(T target, Battle battle) {
		
		if (target instanceof Enemie) {
			int damage = 2*super.getStats().getStrength();
			((Enemie)target).takeDamage(damage);
			
			battle.setMessage("VOCE ATACOU O OPONENTE COM TODA SUA FORCA E VELOCIDADE (-"+damage+"HP)");
			super.getStats().resetOverdrive();
		}
		
	}

}
