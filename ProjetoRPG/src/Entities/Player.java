package Entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyInput;

public class Player extends Stats {
	
	private KeyInput key;
	
	public Player(KeyInput key, String playerClass) {
		
		this.key = key;
		setPlayerValues(playerClass);
		
	}
	
	public void setPlayerValues(String playerClass) {
		
		super.setX(337);
		super.setY(337);
		super.setWalkSpeed(3);
		super.setDirection("down");
		
		switch(playerClass) {
		case "mage":
			getMageSprites();
			break;
		case "warrior":
			break;
		case "healer":
			break;
		case "assassin":
			break;
		}
		
	}
	
	public void getMageSprites() {
		try {
			
			super.setIdleSprites(ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleUp.png"))
								,ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleDown.png"))
								,ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleLeft.png"))
								,ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleRight.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
		brush.drawImage(sprite, super.getX(), super.getY(), 48, 48, null); // Desenha sprite em 48x48 px
		
	}
	
}
