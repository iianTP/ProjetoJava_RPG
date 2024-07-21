package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class Block extends Followers {
	
	private String[][] dialogue = {
			{
				"EsSSStou oCuppaAAdo AAagorRa. mEE dDesculllpe..."
			},
			{
				"Est-...",
				"..."
			}
	};
	
	public Block(GameScreen gs) {
		super(gs);
		
		super.setX(14*48);
		super.setY(14*48);
		this.setSprites();
		
		super.setLocation("world2");
		super.setDialogue(this.dialogue);
		
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/block/block_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/block/block_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/block/block_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/block/block_right.png"))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void action() {
		super.setSprite(super.getIdleSprites()[1]);
	}

	@Override
	public void interaction(Player player) {
		if (super.getState() == 0) {
			if (player.getDirection().equals("down")) {
				super.setSprite(super.getIdleSprites()[0]);
			} else if (player.getDirection().equals("left")) {
				super.setSprite(super.getIdleSprites()[3]);
			} else if (player.getDirection().equals("right")) {
				super.setSprite(super.getIdleSprites()[2]);
			}
			
			super.followerDialogue(player, 3);
		}
		
	}

}
