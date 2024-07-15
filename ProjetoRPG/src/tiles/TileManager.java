package tiles;

import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
	
	private Tile[] tiles;
	
	private int[][] tileNums = new int[65][65];
	
	private String currentMap = "world2";
	
	public TileManager() {
		
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
				this.tiles[tileId-1].setTile(ImageIO.read(getClass().getResourceAsStream("/"+this.currentMap+"/world2_"+tileId+"-"+collision+"_.png")));
				//this.tiles[tileId-1].setTile(ImageIO.read(getClass().getResourceAsStream("/"+this.currentMap+"/"+fileList[i].getName())));
				this.tiles[tileId-1].setCollision((collision.equals("t")) ? true : false);
	
				this.tiles[tileId-1].setName(fileList[i].getName());
				
				System.out.println(tileId);
			}
			
			for (int i = 0; i < fileList.length; i++) {
			
				System.out.println(this.tiles[i].getName());
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void loadTiles() {
		
		InputStream file = getClass().getResourceAsStream("/maps/world2.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(file));
		String ln;
		
		try {
			for(int i = 0; i < 65; i++) {
				
				ln = reader.readLine();
				
				for(int j = 0; j < 65; j++) {
										
					tileNums[i][j] = Integer.parseInt(ln.split(",")[j]);
					
					if (tile(i*48,j*48).checkCollision()) {
						System.out.print("X");
					} else {
						System.out.print(" ");
					}
				
				}
				System.out.println("");
				
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
			
		
	}
	
	public Tile tile(int x, int y){

		//System.out.println(tileNums[x/48][y/48]);
		
		return tiles[tileNums[x/48][y/48]-1];
		
	}
	
	public void draw(Graphics2D brush, int wX, int wY) {
		
		int x, y;
		
		for(int i = 0; i < 65; i++) {
			
			y = i*48 - wY + 720/2;
			
			for(int j = 0; j < 65; j++) {
				
				x = j*48 - wX + 720/2;
				
		//		tile(x,y);
				
			//	tiles[tileNums[i][j]-1]
				
				if (x >= -1*48 && x <= 15*48 && y >= -1*48 && y <= 15*48) {
					
					//if (tile(i*48,j*48).checkCollision()) {
						brush.drawImage(/*tiles[tileNums[i][j]-1]*/tile(i*48,j*48).getTile(), x, y, 48, 48, null);
					//}
					
					
					//brush.drawImage(/*tiles[tileNums[i][j]-1]*/tile(i*48,j*48).getTile(), x, y, 48, 48, null);
				}
				
			}
		
		}

	}
	
}
