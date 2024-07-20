package entities.npcs.bosses;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.enemies.Enemie;
import entities.npcs.Npc;
import entities.player.Player;
import exceptions.InvalidCoordinateException;
import main.screen.GameScreen;

public abstract class BossNpc extends Npc {
	
	private Enemie enemie;

	public BossNpc(GameScreen gs) {
		super(gs);
		
		super.setX(7*48);
		super.setY(3*48);
		
	}
	
	@Override
	public void interaction(Player player) {
		
		if (!player.getInventory().isFull()) {
			super.getGs().startBattle(enemie);
		}
		
	}

	public void vanish() {
		super.setLocation("dead");
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
	
	public void setEnemie(Enemie enemie) {
		this.enemie = enemie;
	}
	
}
