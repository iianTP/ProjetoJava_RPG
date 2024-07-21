package entities.npcs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class CultMember extends Npc {

	public CultMember(GameScreen gs, int x, int y) {
		super(gs);
		
		super.setX(x);
		super.setY(y);
		super.setDialogue(new String[] {"Hmmmmmmmmmm......"});
		super.setLocation("castle1");
		this.setSprites();
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/npcs/cultMember.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interaction(Player player) {
		super.getGs().setDialogueState();
	}
	
	@Override
	public void draw(Graphics2D brush, Player player) {
		
		int x = super.getX() - player.getX() - 24 + super.getGs().getScreenSide()/2;
		int y = super.getY() - player.getY() - 48 + super.getGs().getScreenSide()/2;
		
		setScreenY(y+48);
		
		if (player.getLocation().equals(super.getLocation())) {
		
			// SOMBRA
			brush.setColor(new Color(0,0,0,100));
			brush.fillOval(x, y+86, 48*2, 15);
			//
			
			brush.drawImage(super.getSprite(), x, y, super.getGs().getTileSide()*2, super.getGs().getTileSide()*2, null);
		}

	}

}
