package entities.npcs.inanimates;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.npcs.Npc;
import entities.player.Player;
import main.screen.GameScreen;

public class Door extends Npc {
	
	private int destinyX;
	private int destinyY;
	private int minimumGameStage;
	private String map;
	
	private BufferedImage sprite;

	public Door(GameScreen gs, int destinyX, int destinyY, String map, int x, int y, String location) {
		super(gs);
		this.destinyX = destinyX;
		this.destinyY = destinyY;
		this.map = map;
		
		super.setLocation(location);
		this.setMinimumGameStage();

		super.setX(x);
		super.setY(y);

		setSprites();
	}
	
	private void setMinimumGameStage() {
		switch (this.map) {
		case "world1":
			this.minimumGameStage = 1;
			break;
		case "world2":
			this.minimumGameStage = 2;
			break;
		case "world3":
			this.minimumGameStage = 3;
			break;
		}
	}
	
	@Override
	public void interaction(Player player) {
		if (player.getDirection().equals("up") && player.getGameStage() >= this.minimumGameStage) {
			super.getGs().changeMap(this.map,this.destinyX,this.destinyY);
			player.setDirection("down");
			player.setLocation(this.map);
		}
	}

	@Override
	public void setSprites() {
		String imagePath = "/doors/"+this.map+"_door.png";
		try {
			this.sprite = ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw(Graphics2D brush, Player player) {
		
		int x = super.getX() - player.getX() + super.getGs().getScreenSide()/2;
		int y = super.getY() - player.getY() - 48 + super.getGs().getScreenSide()/2;
		
		setScreenY(y + 48);
		
		if (player.getLocation().equals(super.getLocation())) {
			brush.drawImage(this.sprite, x, y, super.getGs().getTileSide(), super.getGs().getTileSide()*2, null);
		}
		
	}

}
