package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Armor;
import main.screen.GameScreen;

public class Test extends Npc {
	
	public Test(int x, int y, GameScreen gs) {
		super(gs);
		
		super.setDirection("down");

		super.setX(34*48);
		super.setY(32*48);
		
		super.setWalkSpeed(1);
		setSprites();
		
		super.setDialogue(new String[] {"gdsgdsd", "sdgshsrhse"});
		super.setLocation("lobby");
		
	}
	
/*	@Override
	public void action(Player player, Npc[] npcs) {
		
		super.addFrameCounter();
		if (super.getFrameCounter() == 120) {
			
			int randInt = super.rng(100,1);
			
			if (randInt <= 20) {
				super.setDirection("up");
				super.setWalking(true);
			} else if (randInt > 20 && randInt <= 40) {
				super.setDirection("down");
				super.setWalking(true);
			} else if (randInt > 40 && randInt <= 60) {
				super.setDirection("left");
				super.setWalking(true);
			} else if (randInt > 60 && randInt <= 80) {
				super.setDirection("right");
				super.setWalking(true);
			} else if (randInt > 80 && randInt <= 100) {
				super.setWalking(false);
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
		
	}*/
	
	@Override
	public void interaction(Player player) {
		System.out.println("foi");
		super.getGs().setDialogueState();
		
		if (!player.getInventory().isFull()) {
			player.getInventory().addItem(new Armor(1));
		} else {
			System.out.println("inventário está cheio");
		}
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

}
