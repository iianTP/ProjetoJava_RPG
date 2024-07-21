package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class World4Mage extends Npc {
	
	private String direction;

	public World4Mage(GameScreen gs, int x, int y, String startDirection) {
		super(gs);
		super.setX(x);
		super.setY(y);
		this.direction = startDirection;
		super.setDirection(startDirection);
		super.setDialogue(new String[] {"Ola, nunca vi voces por aqui antes, sejam bem vindos!"});
		
		super.setLocation("world3");
		this.setSprites();
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/world4Mage/world4Mage_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/world4Mage/world4Mage_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/world4Mage/world4Mage_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/world4Mage/world4Mage_right.png"))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void action() {
		super.setDirection(this.direction);
		if (super.getDirection().equals("up")) {
			super.setSprite(super.getIdleSprites()[0]);
		} else if (super.getDirection().equals("down")) {
			super.setSprite(super.getIdleSprites()[1]);
		} else if (super.getDirection().equals("left")) {
			super.setSprite(super.getIdleSprites()[2]);
		} else if (super.getDirection().equals("right")) {
			super.setSprite(super.getIdleSprites()[3]);
		}
	}

	@Override
	public void interaction(Player player) {
		
		if (player.getDirection().equals("up")) {
			super.setSprite(super.getIdleSprites()[1]);
		} else if (player.getDirection().equals("down")) {
			super.setSprite(super.getIdleSprites()[0]);
		} else if (player.getDirection().equals("left")) {
			super.setSprite(super.getIdleSprites()[3]);
		} else if (player.getDirection().equals("right")) {
			super.setSprite(super.getIdleSprites()[2]);
		}
		
		super.getGs().setDialogueState();
		
	}

}
