package tiles;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class TileManager {
	
	private Tile[] tiles;
	
	private int[][] tileNums;
	
	private String currentMap = "lobby";
	
	public TileManager() {
		
		this.setTiles();
		this.loadTiles();

	}
	
	public void setCurrentMap(String map) {
		this.currentMap = map;
		this.setTiles();
		this.loadTiles();
	}
	
	public void setTiles() {
		
		File[] fileList = new File("./res/"+this.currentMap).listFiles();
		
		this.tiles = new Tile[fileList.length];
		
		try {
			
			for (int i = 0; i < fileList.length; i++) {
				
				int tileId = Integer.parseInt(fileList[i].getName().split("_")[1].split("-")[0]);
				String collision = fileList[i].getName().split("_")[1].split("-")[1];
				
				this.tiles[tileId-1] = new Tile();
				this.tiles[tileId-1].setTile(ImageIO.read(getClass().getResourceAsStream("/"+this.currentMap+"/"+fileList[i].getName())));
				this.tiles[tileId-1].setCollision((collision.equals("t")) ? true : false);
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void loadTiles() {
		
		InputStream file = getClass().getResourceAsStream("/maps/"+this.currentMap+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		String ln;
		try {
			
			ln = reader.readLine();
			int i = 0;
			this.tileNums = new int[1][ln.split(",").length];
			while (ln != null) {
				
				for(int j = 0; j < ln.split(",").length; j++) {
					tileNums[i][j] = Integer.parseInt(ln.split(",")[j]);
				}
				
				this.tileNums = Arrays.copyOf(this.tileNums, this.tileNums.length+1);
				i++;
				this.tileNums[i]=new int[ln.split(",").length];
				
				ln = reader.readLine();
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
			
		
	}
	
	public Tile tile(int x, int y){

		return tiles[tileNums[x/48][y/48]-1];
		
	}
	
	public void draw(Graphics2D brush, int wX, int wY) {
		
		int x, y;
		
		for(int i = 0; i < this.tileNums.length; i++) {
			
			y = i*48 - wY + 720/2;
			
			for(int j = 0; j < this.tileNums[i].length; j++) {
				
				x = j*48 - wX + 720/2;
				
				if (x >= -1*48 && x <= 15*48 && y >= -1*48 && y <= 15*48 && tileNums[i][j] > 0) {
					
					brush.drawImage(tile(i*48,j*48).getTile(), x, y, 48, 48, null);
						
				}
				
			}
		
		}

	}
	
}
