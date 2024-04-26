package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyInput;

public class Player extends Entity{
	
	private KeyInput key;
	
	
	
	public Player(KeyInput key) {
		this.key = key;
		setPlayerValues();
		getSprites();
	}
	
	public void setPlayerValues() {
		x = 337;
		y = 337;
		walkSpeed = 3;
		facing = "down";
	}
	
	public void getSprites() {
		try {
			
			up = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleUp.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleDown.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleLeft.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleRight.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
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
		
		if (facing.equals("up")) {
			System.out.println(facing);
			sprite = up;
		} else if (facing.equals("down")) {
			System.out.println(facing);
			sprite = down;
		} else if (facing.equals("left")) {
			System.out.println(facing);
			sprite = left;
		} else if (facing.equals("right")) {
			System.out.println(facing);
			sprite = right;
		}
		
		
		brush.drawImage(sprite, x, y, 48, 48, null);
		
	}
	
}
