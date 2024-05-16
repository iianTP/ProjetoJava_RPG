package entities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GameScreen;

public class Entity {
	
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
	
	public Entity(GameScreen gs) {
		this.gs = gs;
	}
	
	// SET
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
	public void setWalkSprites() throws IOException {
		
		this.up1 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkUp1.png"));
		this.up2 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkUp2.png"));
		this.down1 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkDown1.png"));
		this.down2 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkDown2.png"));
		this.left1 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkLeft1.png"));
		this.left2 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkLeft2.png"));
		this.right1 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkRight1.png"));
		this.right2 = ImageIO.read(getClass().getResourceAsStream("/mage/MageWalkRight2.png"));
		
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
	public boolean getCollision() {
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
	
}
