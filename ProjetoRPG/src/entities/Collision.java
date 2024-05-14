package entities;

import tiles.TileManager;

public class Collision {
	
	private TileManager tiles = new TileManager();

	private Npc npcNearby;
	
	public void checkTile(Entity entity) {
		
		if (entity.getHitbox() != null) {
			
			int x = entity.getX();
			int y = entity.getY();
			int speed = entity.getWalkSpeed();
			
			int hitboxTop = y + entity.getHitbox()[0][1];
			int hitboxBottom = y + entity.getHitbox()[1][1];
			int hitboxLeft = x + entity.getHitbox()[0][0];
			int hitboxRight = x + entity.getHitbox()[1][0];
			
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
		
		
	}
	
	public void checkNpc(Entity entity, Npc[] npc) {
		
		if (entity.getHitbox() != null) {
			
			int x = entity.getX();
			int y = entity.getY();
			int speed = entity.getWalkSpeed();
			
			int hitboxTop = y + entity.getHitbox()[0][1];
			int hitboxBottom = y + entity.getHitbox()[1][1];
			int hitboxLeft = x + entity.getHitbox()[0][0];
			int hitboxRight = x + entity.getHitbox()[1][0];
			
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
				
				if (entity.getCollision()) {
					this.npcNearby = npc[i];
				}
				
			}
			
		}
		
	}

	public Npc getNpcNearby() {
		return this.npcNearby;
	}
	
	public void checkPlayer(Npc npc, Player player) {
		
		if (npc.getHitbox() != null) {
			
			int x = npc.getX();
			int y = npc.getY();
			int speed = npc.getWalkSpeed();
			
			int hitboxTop = y + npc.getHitbox()[0][1];
			int hitboxBottom = y + npc.getHitbox()[1][1];
			int hitboxLeft = x + npc.getHitbox()[0][0];
			int hitboxRight = x + npc.getHitbox()[1][0];
			
			if (npc.getDirection().equals("up")) {
				
				if (player.checkHitbox(hitboxLeft, hitboxTop - speed) ||
					player.checkHitbox(hitboxRight, hitboxTop - speed)) {
					npc.setCollision(true);
				}
				
			} else if (npc.getDirection().equals("down")) {
				
				if (player.checkHitbox(hitboxLeft, hitboxBottom + speed) ||
					player.checkHitbox(hitboxRight, hitboxBottom + speed)) {
					npc.setCollision(true);
				}
				
			} else if (npc.getDirection().equals("left")) {
				
				if (player.checkHitbox(hitboxLeft - speed, hitboxTop) ||
					player.checkHitbox(hitboxLeft - speed, hitboxBottom)) {
					npc.setCollision(true);
				}
				
			} else if (npc.getDirection().equals("right")) {
				
				if (player.checkHitbox(hitboxRight + speed, hitboxTop) ||
					player.checkHitbox(hitboxRight + speed, hitboxBottom)) {
					npc.setCollision(true);
				}
				
			} 
			
		}
		
		
	}
	
}
