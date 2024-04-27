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
		
		x = 337;
		y = 337;
		walkSpeed = 3;
		facing = "down";
		
		switch(playerClass) {
		case "mage":
			getSprites();
			break;
		case "warrior":
			break;
		case "healer":
			break;
		case "assassin":
			break;
		}
		
	}
	
	public void getSprites() {
		try {
			
			idleUp = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleUp.png"));
			idleDown = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleDown.png"));
			idleLeft = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleLeft.png"));
			idleRight = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleRight.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		// Caminhada
		if (key.up) {
			facing = "up";
			y -= 3;
		} else if (key.down) {
			facing = "down";
			y += 3;
		} else if (key.left) {
			facing = "left";
			x -= 3;
		} else if (key.right) {
			facing = "right";
			x += 3;
		}
		
	}
	
	
	
	public void draw(Graphics2D brush) {
		
		BufferedImage sprite = null;
		
		// Atualização de sprites do player
		if (facing.equals("up")) {
			sprite = idleUp;
		} else if (facing.equals("down")) {
			sprite = idleDown;
		} else if (facing.equals("left")) {
			sprite = idleLeft;
		} else if (facing.equals("right")) {
			sprite = idleRight;
		}
	
		brush.drawImage(sprite, x, y, 48, 48, null); // Desenha sprite em 48x48 px
		
	}
	
}
