package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.Boss2;
import entities.enemies.Boss3;
import entities.enemies.Enemie;
import entities.player.Player;
import exceptions.InvalidCoordinateException;
import main.screen.GameScreen;

public class Boss3Npc extends Npc {
	
	private Enemie enemie;

	public Boss3Npc(GameScreen gs) {
		super(gs);
		this.enemie = new Boss3(super.getGs());
		super.setLocation("castle3");
		
		try {
			super.setX(6*48+24);
			super.setY(2*48);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}
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

}
