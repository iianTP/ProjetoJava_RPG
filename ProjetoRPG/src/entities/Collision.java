package entities;

import tiles.TileManager;

public class Collision {
	
	private int[][] hitbox = {{12, 30}, {33, 45}};
	
	private TileManager tiles = new TileManager();
	
	public void checkTile(Entity entity) {
		
		int x = entity.getX() - 24;
		int y = entity.getY() - 24;
		int speed = entity.getWalkSpeed();
		
		int hitboxTop = y + this.hitbox[0][1];
		int hitboxBottom = y + this.hitbox[1][1];
		
		int hitboxLeft = x + this.hitbox[0][0];
		int hitboxRight = x + this.hitbox[1][0];
		
		if (entity.getDirection().equals("up")) {
			
			if (this.tiles.tile(hitboxLeft, hitboxTop - speed).checkCollision() ||
				this.tiles.tile(hitboxRight, hitboxTop - speed).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else if (entity.getDirection().equals("down")) {
			
			if (this.tiles.tile(hitboxLeft, hitboxBottom + speed).checkCollision() ||
				this.tiles.tile(hitboxRight, hitboxBottom + speed).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else if (entity.getDirection().equals("left")) {
			
			if (this.tiles.tile(hitboxLeft - speed, hitboxTop).checkCollision() ||
				this.tiles.tile(hitboxLeft - speed, hitboxBottom).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else if (entity.getDirection().equals("right")) {
			
			if (this.tiles.tile(hitboxRight + speed, hitboxTop).checkCollision() ||
				this.tiles.tile(hitboxRight + speed, hitboxBottom).checkCollision()) {
				entity.setCollision(true);
			} else {
				entity.setCollision(false);
			}
			
		} else {
			entity.setCollision(false);
		}
		
	}
	
	public void checkNpc(Entity entity, Npc[] npc) {
		
		int x = entity.getX() - 24;
		int y = entity.getY() - 24;
		int speed = entity.getWalkSpeed();
		
		int hitboxTop = y + this.hitbox[0][1];
		int hitboxBottom = y + this.hitbox[1][1];
		
		int hitboxLeft = x + this.hitbox[0][0];
		int hitboxRight = x + this.hitbox[1][0];
		
		
		for (int i = 0; i < npc.length; i++) {

			if (entity.getDirection().equals("up")) {
				
				if (npc[i].checkHitbox(hitboxLeft, hitboxTop - speed) ||
					npc[i].checkHitbox(hitboxRight, hitboxTop - speed)) {
					entity.setCollision(true);
				}
				
			} else if (entity.getDirection().equals("down")) {
				
				if (npc[i].checkHitbox(hitboxLeft, hitboxBottom + speed) ||
					npc[i].checkHitbox(hitboxRight, hitboxBottom + speed)) {
					entity.setCollision(true);
				}
				
			} else if (entity.getDirection().equals("left")) {
				
				if (npc[i].checkHitbox(hitboxLeft - speed, hitboxTop) ||
					npc[i].checkHitbox(hitboxLeft - speed, hitboxBottom)) {
					entity.setCollision(true);
				}
				
			} else if (entity.getDirection().equals("right")) {
				
				if (npc[i].checkHitbox(hitboxRight + speed, hitboxTop) ||
					npc[i].checkHitbox(hitboxRight + speed, hitboxBottom)) {
					entity.setCollision(true);
				}
				
			}
			
		}	
		
	}
	
}
