package entities.npcs.teammates;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Stats;
import entities.npcs.Npc;
import entities.player.Player;
import main.screen.GameScreen;

public class AssassinNpc extends Npc {
	
	private Stats stats = new Stats();
	
	public AssassinNpc(int x, int y, GameScreen gs) {
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

	}

	@Override
	public void interaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void action(Player player, Npc[] npcs) {
		
		super.addFrameCounter();
		if (super.getFrameCounter() == 120) {
			
			int randInt = super.rng(100, 1);
			
			if (randInt <= 20) {
				super.setDirection("up");
			} else if (randInt > 20 && randInt <= 40) {
				super.setDirection("down");
			} else if (randInt > 40 && randInt <= 60) {
				super.setDirection("left");
			} else if (randInt > 60 && randInt <= 80) {
				super.setDirection("right");
			}

			super.resetFrameCounter();
		}
		
		super.setCollision(false);
		super.collision().checkTile(this);
		super.collision().checkNpc(this, npcs);
		super.collision().checkPlayer(this, player);
		
		if (!super.isColliding() && super.isWalking()) {
			if (super.getDirection().equals("up")) {
				super.goUp();
			} else if (super.getDirection().equals("down")) {
				super.goDown();
			} else if (super.getDirection().equals("left")) {
				super.goLeft();
			} else if (super.getDirection().equals("right")) {
				super.goRight();
			}
		}
		
	}

}
