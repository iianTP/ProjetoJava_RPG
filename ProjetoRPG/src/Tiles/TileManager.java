package Tiles;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class TileManager {
	
	private Tile[] tiles;
	private int[][] tileNums;
	
	public TileManager() {
		
		tiles = new Tile[2];
		tileNums = new int[15][15];
		
		this.setTiles();
		this.loadTiles();
		
	}
	
	public void setTiles() {
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
	
	public void loadTiles() {
		
		InputStream file = getClass().getResourceAsStream("/maps/teste.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		
		try {
			for(int i = 0; i < 15; i++) {
				
				String ln = reader.readLine();
				
				for(int j = 0; j < 15; j++) {
					
					tileNums[i][j] = Integer.parseInt(ln.split(",")[j]);
					
				}
			}
		} catch (Exception e) {
			
		}
			
		
	}
	
	public void draw(Graphics2D brush) {
		
		for(int i = 0; i < 15; i++) {
			
			for(int j = 0; j < 15; j++) {
				
				brush.drawImage(tiles[tileNums[i][j]-1].getTile(), i*48, j*48, 48, 48, null);
				tiles[tileNums[i][j]-1].setTileX(i*48);
				tiles[tileNums[i][j]-1].setTileY(i*48);
				
			}
		
		}

	}
	
}
