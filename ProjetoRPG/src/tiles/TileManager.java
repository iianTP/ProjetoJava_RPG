package tiles;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.ScreenInfo;

public class TileManager {
	
	private Tile[] tiles;
	private ScreenInfo screen = new ScreenInfo();
	
	private int[][] tileNums = new int[45][45];
	
	public TileManager() {
		
		this.setTiles();
		this.loadTiles();

	}
	
	public void setTiles() {
		
		File[] fileList = new File("./map/textures").listFiles();
		
		this.tiles = new Tile[fileList.length];
		
		try {
			
			for (int i = 0; i < fileList.length; i++) {
				
				int tileId = Integer.parseInt(fileList[i].getName().split("_")[1].split("-")[0]);
				String collision = fileList[i].getName().split("_")[1].split("-")[1];
				
				this.tiles[tileId-1] = new Tile();
				this.tiles[tileId-1].setTile(ImageIO.read(getClass().getResourceAsStream("/textures/sprite_"+tileId+"-"+collision+"_.png")));
				this.tiles[tileId-1].setCollision((collision.equals("t")) ? true : false);
	
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void loadTiles() {
		
		InputStream file = getClass().getResourceAsStream("/maps/lobby.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		String ln;
		
		try {
			for(int i = 0; i < 45; i++) {
				
				ln = reader.readLine();
				
				for(int j = 0; j < 45; j++) {
										
					tileNums[i][j] = Integer.parseInt(ln.split(",")[j]);
					
				}
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
		
		for(int i = 0; i < 45; i++) {
			
			y = i*this.screen.tileSide() - wY + this.screen.screenSide()/2;
			
			for(int j = 0; j < 45; j++) {
				
				x = j*this.screen.tileSide() - wX + this.screen.screenSide()/2;
				
				brush.drawImage(tiles[tileNums[i][j]-1].getTile(), x, y, this.screen.tileSide(), this.screen.tileSide(), null);
				
			}
		
		}

	}
	
}
