package Tiles;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TileManager {
	
	Tile[] tiles;
	
	public TileManager() {
		tiles = new Tile[2];
		this.getTiles();
	}
	
	public void getTiles() {
		try {
			
			tiles[0] = new Tile();
			tiles[0]
			.setTile(ImageIO.read(getClass().getResourceAsStream("/grasses/grass.png")));
			tiles[0].setWalkable(true);
			
			tiles[1] = new Tile();
			tiles[1]
			.setTile(ImageIO.read(getClass().getResourceAsStream("/walls/wall.png")));
			tiles[1].setWalkable(false);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void draw(Graphics2D brush) {
		
		for(int i = 0; i < 15; i++) {
			
			for(int j = 0; j < 15; j++) {
				
				brush.drawImage(tiles[0].getTile(), i*48, j*48, 48, 48, null);
				
			}
		
		}
		
		brush.drawImage(tiles[1].getTile(), 48, 48, 48, 48, null);
		
	}
}
