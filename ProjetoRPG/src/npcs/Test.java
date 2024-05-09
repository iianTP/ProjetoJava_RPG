package npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Npc;

public class Test extends Npc {
	
	public Test(int x, int y) {
		
		super.setDirection("down");
		
		super.setX(x);
		super.setY(y);
		
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
