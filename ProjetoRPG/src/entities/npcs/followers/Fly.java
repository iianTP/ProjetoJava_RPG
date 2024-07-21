package entities.npcs.followers;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class Fly extends Follower {
	
	private String[][] dialogue = {
			{
				"Me deixe em paz."
			},
			{
				"O que e isso que voce achou?","...!","Hmm...ele estava certo...?"
			}
	};

	
	public Fly(GameScreen gs) {
		super(gs);
		super.setX(49*48);
		super.setY(13*48);
		this.setSprites();
		super.setDialogue(this.dialogue);
		super.setLocation("world1");
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/fly/fly_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/fly/fly_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/fly/fly_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/fly/fly_right.png"))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void action() {
		super.setSprite(super.getIdleSprites()[3]);
	}
	
	@Override
	public void interaction(Player player) {
		
		if (super.getState() == 0) {
			
			if (player.getDirection().equals("down")) {
				super.setSprite(super.getIdleSprites()[0]);
			} else if (player.getDirection().equals("up")) {
				super.setSprite(super.getIdleSprites()[1]);
			} else if (player.getDirection().equals("right")) {
				super.setSprite(super.getIdleSprites()[2]);
			}
			
			super.followerDialogue(player, 2);
		
		}
		
	}

}
