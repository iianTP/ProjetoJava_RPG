package Tiles;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage tile;
	
	private int tileX, tileY;
	
	private boolean collision;
	
	// SET
	public void setTile(BufferedImage tile) {
		this.tile = tile;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}
	
	// GET
	public BufferedImage getTile() {
		return this.tile;
	}
	public int getTileX() {
		return this.tileX;
	}
	public int getTileY() {
		return this.tileY;
	}
	
	public boolean checkCollision() {
		return collision;
	}
}
