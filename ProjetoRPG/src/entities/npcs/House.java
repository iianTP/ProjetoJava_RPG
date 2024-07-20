package entities.npcs;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidGameStateIndex;
import main.screen.GameScreen;

public class House extends Npc {

	public House(GameScreen gs, int x, int y, String location) {
		super(gs);
		super.setLocation(location);
		super.setDialogue(new String[] {"(Uma casa, nao e sua)"});
		
		super.setX(x);
		super.setY(y);
		
		setHitbox();
		this.setSprites();
	}
	
	@Override
	public void interaction(Player player) {
		super.getGs().setDialogueState();
	}
	
	private void setHitbox() {
		if (super.getLocation().equals("world1") || super.getLocation().equals("world3")) {
			super.setHitbox(new int[][] {{-24, 0},{48+24,48}});
		} else if (super.getLocation().equals("world2")) {
			super.setHitbox(new int[][] {{-48, 0},{48*2,48}});
		}
	}

	@Override
	public void setSprites() {
		
		String imagePath = "/houses/"+super.getLocation()+"_house.png";
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream(imagePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void draw(Graphics2D brush, Player player) {
		
		int x = super.getX() - player.getX() - 48 + super.getGs().getScreenSide()/2;
		int y = super.getY() - player.getY() - 48*3 + super.getGs().getScreenSide()/2;
		
		setScreenY(y + 48*3);
		
		if (player.getLocation().equals(super.getLocation())) {
			brush.drawImage(super.getSprite(), x, y, super.getGs().getTileSide()*3, super.getGs().getTileSide()*4, null);
		}
		
	}

	

}
