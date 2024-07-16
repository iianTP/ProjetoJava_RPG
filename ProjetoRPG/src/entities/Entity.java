package entities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import exceptions.InvalidCoordinateException;
import interfaces.IEntitySetup;
import main.screen.GameScreen;

public abstract class Entity implements IEntitySetup {
	
	private GameScreen gs;
	
	// Coordenadas no mapa e velocidade
	private int x, y;
	private int walkSpeed;
	
	private int spriteCount = 0;
	private int animationFrame = 1;
	
	// Sprites
	private BufferedImage idleUp, idleDown, idleRight, idleLeft;
	private BufferedImage up1, down1, right1, left1;
	private BufferedImage up2, down2, right2, left2;
	
	// Direção
	private String direction;
	
	private boolean collision;
	// private Collision collision = new Collision();
	
	private final Random rng = new Random();
	
	private String location;
	
	public Entity(GameScreen gs) {
		this.gs = gs;
	}
	
	public int rng(int range, int minNum) {
		return this.rng.nextInt(range) + minNum;
	}
	
	// SET
	public void setX(int x) throws InvalidCoordinateException {
		if (x < 0) {
			throw new InvalidCoordinateException("coordenada x < 0");
		}
		this.x = x;
	}
	public void setY(int y) throws InvalidCoordinateException {
		if (y < 0) {
			throw new InvalidCoordinateException("coordenada y < 0");
		}
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
	public void setWalkSprites(BufferedImage up1, BufferedImage up2,
			  				   BufferedImage down1,BufferedImage down2,
			  				   BufferedImage left1,BufferedImage left2,
			  				   BufferedImage right1,BufferedImage right2) throws IOException {
		
		this.up1 = up1;
		this.up2 = up2;
		this.down1 = down1;
		this.down2 = down2;
		this.left1 = left1;
		this.left2 = left2;
		this.right1 = right1;
		this.right2 = right2;
		
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	// GET
	public int[][] getHitbox() {return null;}
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
	public BufferedImage[][] getWalkSprites() {
		return new BufferedImage[][] {{this.up1, this.down1, this.left1, this.right1},
									  {this.up2, this.down2, this.left2, this.right2}};
	}
	public boolean isColliding() {
		return this.collision;
	}
	public int getSpriteCount() {
		return this.spriteCount;
	}
	public int getAnimationFrame() {
		return this.animationFrame;
	}
	//
	
	public void goUp() {
		this.y -= this.walkSpeed;
	}
	public void goDown() {
		this.y += this.walkSpeed;
	}
	public void goLeft() {
		this.x -= this.walkSpeed;
	}
	public void goRight() {
		this.x += this.walkSpeed;
	}
	
	
	public void addSpriteCount() {
		this.spriteCount++;
	}
	public void resetSpriteCount() {
		this.spriteCount = 0;
	}
	public void switchAnimationFrame() {
		this.animationFrame = (this.animationFrame == 1) ? 2 : 1;
	}

	public GameScreen getGs() {
		return gs;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
