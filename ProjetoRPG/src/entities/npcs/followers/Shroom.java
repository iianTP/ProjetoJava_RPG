package entities.npcs.followers;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class Shroom extends Follower {
	
	private String[][] dialogue = {
			{
				"Vao procurar o que fazer..."
			},
			{
				"...",
				"Ola, nunca vi voces por aqui antes, sejam bem vindos!"
			}
	};
	
	public Shroom(GameScreen gs) {
		super(gs);
		
		super.setX(14*48);
		super.setY(31*48);
		super.setLocation("world3");
		this.setSprites();
		super.setDialogue(this.dialogue);
		
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/shroom/shroom_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/shroom/shroom_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/shroom/shroom_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/shroom/shroom_right.png"))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void action() {
		super.setSprite(super.getIdleSprites()[2]);
	}

	@Override
	public void interaction(Player player) {
		if (super.getState() == 0) {
			if (player.getDirection().equals("down")) {
				super.setSprite(super.getIdleSprites()[0]);
			} else if (player.getDirection().equals("up")) {
				super.setSprite(super.getIdleSprites()[1]);
			} else if (player.getDirection().equals("left")) {
				super.setSprite(super.getIdleSprites()[3]);
			}
			super.followerDialogue(player, 4);
		}
	}

}
