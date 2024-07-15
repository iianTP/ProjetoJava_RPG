package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import exceptions.InvalidCoordinateException;
import main.screen.GameScreen;

public class Door extends Npc {
	
	private int destinyX;
	private int destinyY;
	private String map;

	public Door(GameScreen gs, int destinyX,int destinyY, String map) {
		super(gs);
		this.destinyX = destinyX;
		this.destinyY = destinyY;
		this.map = map;
		
		super.setDirection("down");
		
		try {
			super.setX(32*48);
			super.setY(32*48);
		} catch (InvalidCoordinateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSprites();
	}
	
	@Override
	public void interaction() {
		super.getGs().changeMap(this.map,this.destinyX,this.destinyY);
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleUp.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleDown.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleLeft.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleRight.png"))
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void action(Player player, Npc[] npcs) {
		// TODO Auto-generated method stub
		
	}

}
