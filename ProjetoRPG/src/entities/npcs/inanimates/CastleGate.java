package entities.npcs.inanimates;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.npcs.Npc;
import entities.player.Player;
import main.screen.GameScreen;

public class CastleGate extends Npc {
	
	private String destiny;
	private int destinyX;
	private int destinyY;

	public CastleGate(GameScreen gs, String location, String destiny, int destinyX, int destinyY) {
		super(gs);
		super.setX(7*48);
		super.setY(44*48);
		super.setLocation(location);
		super.setHitbox(new int[][] {{0,0},{48,48}});
		
		this.destiny = destiny;
		this.destinyX = destinyX;
		this.destinyY = destinyY;
		
		this.setSprites();
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/doors/castle_door.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interaction(Player player) {
		super.getGs().changeMap(this.destiny, this.destinyX, this.destinyY);
		player.setDirection("down");
	}
	
	@Override
	public void draw(Graphics2D brush, Player player) {
		
		int x = super.getX() - player.getX() + super.getGs().getScreenSide()/2;
		int y = super.getY() - player.getY() + super.getGs().getScreenSide()/2;
		
		if (player.getLocation().equals(super.getLocation())) {
			brush.drawImage(super.getSprite(), x, y, 48, 48, null);
		}
		
	}
}
