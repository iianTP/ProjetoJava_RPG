package entities;

import java.awt.Graphics2D;

import main.ScreenInfo;
import quests.Quest;

public class Npc extends Entity {
	
	private String[] dialogue;
	private Quest[] quests;
	private Collision collision;
	
	private ScreenInfo screen = new ScreenInfo();
	
	private int[][] hitbox = {{12, 30}, {33, 45}};
	
	public void draw(Graphics2D brush, int wX, int wY) {
		
		int x = super.getX() - wX + this.screen.screenSide()/2;
		int y = super.getY() - wY + this.screen.screenSide()/2;
		
		brush.drawImage(super.getIdleSprites()[1], x, y, this.screen.tileSide(), this.screen.tileSide(), null);

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
	
}
