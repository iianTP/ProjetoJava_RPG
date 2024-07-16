package entities.npcs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.Boss1;
import entities.enemies.Enemie;
import entities.player.Player;
import exceptions.InvalidCoordinateException;
import main.screen.GameScreen;

public class Boss1Npc extends Npc {

	private Enemie enemie;

	public Boss1Npc(GameScreen gs) {
		super(gs);
		this.enemie = new Boss1(super.getGs());
		super.setLocation("castle1");
		
		try {
			super.setX(6*48+24);
			super.setY(2*48);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}
		
		setSprites();
	}
	
	@Override
	public void interaction(Player player) {
		
		if (!player.getInventory().isFull()) {
			super.getGs().startBattle(enemie);
		}
		
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleDown.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void vanish() {
		super.setLocation("dead");
	}

	@Override
	public void draw(Graphics2D brush, Player player) {
		
		int x = super.getX() - player.getX() + super.getGs().getScreenSide()/2;
		int y = super.getY() - player.getY() + super.getGs().getScreenSide()/2;
		
		setScreenY(y+48);
		
		if (player.getLocation().equals(super.getLocation())) {
		
			// SOMBRA
			brush.setColor(new Color(0,0,0,100));
			brush.drawRect(x, y, 48*2, 48*2);
			brush.fillOval(x, y+40, 48*2, 15);
			//
			
			brush.drawImage(super.getSprite(), x, y, super.getGs().getTileSide()*2, super.getGs().getTileSide()*2, null);
		}

	}

}
