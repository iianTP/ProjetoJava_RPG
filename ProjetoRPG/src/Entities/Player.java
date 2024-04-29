package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GameScreen;
import main.KeyInput;

public class Player extends Stats {
	
	private KeyInput key;
	
	private GameScreen gs = new GameScreen();
	
	public Player(KeyInput key) {
		
		this.key = key;
		super.setX(337);
		super.setY(337);
		super.setWalkSpeed(3);
		super.setDirection("down");
		
	}
	
	public void update() {
		
		// Caminhada
		if (key.up) {
			super.setDirection("up");
			super.setY(super.getY()-super.getWalkSpeed());
		} else if (key.down) {
			super.setDirection("down");
			super.setY(super.getY()+super.getWalkSpeed());
		} else if (key.left) {
			super.setDirection("left");
			super.setX(super.getX()-super.getWalkSpeed());
		} else if (key.right) {
			super.setDirection("right");
			super.setX(super.getX()+super.getWalkSpeed());
		}
		
	}

	public void draw(Graphics2D brush) {
		
		BufferedImage sprite = null;
		
		// Atualização de sprites do player
		if (super.getDirection().equals("up")) {
			sprite = super.getIdleSprites()[0];
		} else if (super.getDirection().equals("down")) {
			sprite = super.getIdleSprites()[1];
		} else if (super.getDirection().equals("left")) {
			sprite = super.getIdleSprites()[2];
		} else if (super.getDirection().equals("right")) {
			sprite = super.getIdleSprites()[3];
		}
	
		brush.drawImage(sprite, super.getX(), super.getY(), gs.getTileResolution(), gs.getTileResolution(), null); // Desenha sprite em 48x48 px
		
	}
	
}
