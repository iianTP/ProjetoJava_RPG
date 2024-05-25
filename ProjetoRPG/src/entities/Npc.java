package entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GameScreen;
import main.ScreenInfo;
import quests.Quest;

public class Npc extends Entity {
	
	private String[] dialogue;
	private Quest[] quests;
	private Collision collision = new Collision();
	
	private ScreenInfo screen = new ScreenInfo();
	
	private String location;
	
	private int screenX;
	private int screenY;
	private int[][] hitbox = {{12, 30}, {33, 45}};
	private int frameCounter = 0;
	
	private boolean walking = false;
	
	public Npc(GameScreen gs) {
		super(gs);
	}
	
	public void interaction() {}
	public void action(Player player, Npc[] npcs) {}
	public void update(Player player, Npc[] npcs) {
		action(player, npcs);
	}
	
	public void draw(Graphics2D brush, int wX, int wY) {
		
		BufferedImage sprite = null;
		
		if (super.getDirection().equals("up")) {
			sprite = super.getIdleSprites()[0];
		} else if (super.getDirection().equals("down")) {
			sprite = super.getIdleSprites()[1];
		} else if (super.getDirection().equals("left")) {
			sprite = super.getIdleSprites()[2];
		} else if (super.getDirection().equals("right")) {
			sprite = super.getIdleSprites()[3];
		}
		/*
		if (super.getDirection().equals("idle")) {
			sprite = super.getIdleSprites()[1];
		}
		*/
		this.screenX = super.getX() - wX + this.screen.screenSide()/2;
		setScreenY(super.getY() - wY + this.screen.screenSide()/2);
		
		brush.drawImage(sprite, this.screenX, this.screenY, this.screen.tileSide(), this.screen.tileSide(), null);

	}
	
	public boolean checkHitbox(int playerX, int playerY) {
		
		int x = super.getX();
		int y = super.getY();
		
		if (playerX > hitbox[0][0] + x && playerX < hitbox[1][0] + x && 
			playerY > hitbox[0][1] + y && playerY < hitbox[1][1] + y) {
			return true;
		} else {
			return false;
		}
		
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
	
}
