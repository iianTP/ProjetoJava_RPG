package Entities;

import java.awt.image.BufferedImage;

public class Entity {
	
	// Coordenadas e velocidade
	private int x, y;
	private int walkSpeed;
	
	// Sprites
	private BufferedImage idleUp, idleDown, idleRight, idleLeft;
	private BufferedImage up1, down1, right1, left1;
	private BufferedImage up2, down2, right2, left2;
	
	// Direção
	private String direction = "down";
	
	// Parado ou andando
	private boolean idle;
	
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWalkSpeed(int walkSpeed) {
		this.walkSpeed = walkSpeed;
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setIdleSprites(BufferedImage idleUp
							  ,BufferedImage idleDown
							  ,BufferedImage idleLeft
							  ,BufferedImage idleRight) {
		
		this.idleUp = idleUp;
		this.idleDown = idleDown;
		this.idleLeft = idleLeft;
		this.idleRight = idleRight;
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWalkSpeed() {
		return this.walkSpeed;
	}
	
	public String getDirection() {
		return this.direction;
	}
	public BufferedImage[] getIdleSprites() {
		return new BufferedImage[] {this.idleUp, this.idleDown, this.idleLeft, this.idleRight};
	}
	
}
