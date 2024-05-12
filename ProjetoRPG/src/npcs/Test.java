package npcs;

import java.io.IOException;

import java.util.Random;

import javax.imageio.ImageIO;

import entities.Npc;
import entities.Player;

public class Test extends Npc {
	
	
	
	public Test(int x, int y) {
		
		super.setDirection("down");

		super.setX(x);
		super.setY(y);
		super.setWalkSpeed(3);
		
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
	
	@Override
	public void action(Player player) {
		super.addFrameCounter();
		if (super.getFrameCounter() == 120) {
			Random random = new Random();
			int randInt = random.nextInt(100)+1;
			
			if (randInt <= 25) {
				super.setDirection("up");
			} else if (randInt > 25 && randInt <= 50) {
				super.setDirection("down");
			} else if (randInt > 50 && randInt <= 75) {
				super.setDirection("left");
			} else if (randInt > 75 && randInt <= 100) {
				super.setDirection("right");
			}

			super.resetFrameCounter();
		}
		
		super.setCollision(false);
		super.collision().checkTile(this);
		
		if (!super.getCollision()) {
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
	
	@Override
	public void interaction() {
		System.out.println("foi");
	}
	
}
