package tiles;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage tile;
	
	private boolean collision;
	
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

}
