package entities.npcs;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import exceptions.InvalidCoordinateException;
import main.screen.GameScreen;

public class Castle extends Npc {
	
	private BufferedImage sprite;
	
	private String castle;

	public Castle(GameScreen gs, String map, String castle, int x, int y) {
		super(gs);
		this.castle = castle;
		setSprites();
		super.setLocation(map);
		super.setHitbox(new int[][] {{-48, 0},{48*2,48}});
		
		try {
			super.setX(x);
			super.setY(y);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setSprites() {
		String imagePath = "/castles/"+this.castle+".png";
		try {
			this.sprite = ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void interaction(Player player) {
		if (player.getDirection().equals("up")) {
			super.getGs().changeMap(this.castle, 7*48+24, 43*48+24);
			player.setLocation(this.castle);
		}
	}

	@Override
	public void draw(Graphics2D brush, Player player) {
		
		int x = super.getX() - player.getX() -48+ super.getGs().getScreenSide()/2;
		int y = super.getY() - player.getY() - 48*3 + super.getGs().getScreenSide()/2;
		
		setScreenY(y + 48*3);
		
		if (player.getLocation().equals(super.getLocation())) {
			brush.drawImage(this.sprite, x, y, super.getGs().getTileSide()*3, super.getGs().getTileSide()*4, null);
		}
		
	}

}
