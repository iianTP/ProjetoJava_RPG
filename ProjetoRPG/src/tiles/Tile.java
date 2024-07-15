package tiles;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage tile;
	
	private boolean collision;
	
	private String name;
	
	// SET
	public void setTile(BufferedImage tile) {
		this.tile = tile;
	}
	public void setCollision(boolean collision) {
		this.collision = collision;
	}
	
	// GET
	public BufferedImage getTile() {
		return this.tile;
	}

	
	public boolean checkCollision() {
		return collision;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
