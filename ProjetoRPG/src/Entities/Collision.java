package Entities;

import Tiles.TileManager;

public class Collision {
	
	private int[][] hitbox = {{12, 30}, {33, 45}};
	
	private TileManager tiles = new TileManager();
	
	public void collision(Entity entity) {
		
		int x = entity.getX();
		int y = entity.getY();
		int speed = entity.getWalkSpeed();
		
		if (entity.getDirection().equals("up")) {
			
			if (this.tiles.tile(x + this.hitbox[0][0], y + this.hitbox[0][1] - speed).checkCollision() ||
				this.tiles.tile(x + this.hitbox[1][0], y + this.hitbox[0][1] - speed).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else if (entity.getDirection().equals("down")) {
			
			if (this.tiles.tile(x + this.hitbox[0][0], y + this.hitbox[1][1] + speed).checkCollision() ||
				this.tiles.tile(x + this.hitbox[1][0], y + this.hitbox[1][1] + speed).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else if (entity.getDirection().equals("left")) {
			
			if (this.tiles.tile(x + this.hitbox[0][0] - speed, y + this.hitbox[0][1]).checkCollision() ||
				this.tiles.tile(x + this.hitbox[0][0] - speed, y + this.hitbox[1][1]).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else if (entity.getDirection().equals("right")) {
			
			if (this.tiles.tile(x + this.hitbox[1][0] + speed, y + this.hitbox[0][1]).checkCollision() ||
				this.tiles.tile(x + this.hitbox[1][0] + speed, y + this.hitbox[1][1]).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else {
			entity.setCollision(false);
		}
		
	}
	
}
