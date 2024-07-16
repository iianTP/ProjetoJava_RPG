package entities.npcs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entities.Collision;
import entities.Entity;
import entities.player.Player;
import main.screen.GameScreen;

public abstract class Npc extends Entity {
	
	private String[] dialogue;
	private Collision collision = new Collision();
	
	private int screenX;
	private int screenY;
	private int[][] hitbox = {{12, 30}, {33, 45}};
	private int frameCounter = 0;
	
	private boolean walking = false;
	
	private BufferedImage sprite;
	
	public Npc(GameScreen gs) {
		super(gs);
	}
	
//	public void interaction() {};
	public void interaction(Player player) {};
	public void action(Player player, Npc[] npcs) {};
	
	public void update(Player player, Npc[] npcs) {
		action(player, npcs);
	}
	
	public void draw(Graphics2D brush, Player player) {
		
		this.screenX = super.getX() - player.getX() + super.getGs().getScreenSide()/2;
		setScreenY(super.getY() - player.getY() + super.getGs().getScreenSide()/2);
		
		if (player.getLocation().equals(super.getLocation())) {
		
			// SOMBRA
			brush.setColor(new Color(0,0,0,100));
			brush.drawRect(this.screenX, this.screenY, 48, 48);
			brush.fillOval(this.screenX, this.screenY+40, 48, 15);
			//
			
			brush.drawImage(sprite, this.screenX, this.screenY, super.getGs().getTileSide(), super.getGs().getTileSide(), null);
		}

	}
	
	public boolean checkHitbox(int playerX, int playerY) {
		
		int x = super.getX();
		int y = super.getY();
		
		if (playerX >= hitbox[0][0] + x && playerX <= hitbox[1][0] + x && 
			playerY >= hitbox[0][1] + y && playerY <= hitbox[1][1] + y) {
			return true;
		} else {
			return false;
		}
		
	}

	public int getFrameCounter() {
		return frameCounter;
	}
	public void addFrameCounter() {
		this.frameCounter++;
	}
	public void resetFrameCounter() {
		this.frameCounter = 0;
	}
	public Collision collision() {
		return this.collision;
	}
	
	@Override
	public int[][] getHitbox() {
		return this.hitbox;
	}
	
	public void setHitbox(int[][] hitbox) {
		this.hitbox = hitbox;
	}

	public int getScreenX() {
		return screenX;
	}

	public void setScreenX(int screenX) {
		this.screenX = screenX;
	}

	public int getScreenY() {
		return this.screenY;
	}

	public void setScreenY(int screenY) {
		this.screenY = screenY;
	}

	public boolean isWalking() {
		return walking;
	}

	public void setWalking(boolean walking) {
		this.walking = walking;
	}

	public String[] getDialogue() {
		return dialogue;
	}

	public void setDialogue(String[] dialogue) {
		this.dialogue = dialogue;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
}
