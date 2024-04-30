package Tiles;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage tile;
	
	private boolean walkable;
	
	// SET
	public void setTile(BufferedImage tile) {
		this.tile = tile;
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	// GET
	public BufferedImage getTile() {
		return this.tile;
	}
	
	// IS
	public boolean isWalkable() {
		return walkable;
	}
}
