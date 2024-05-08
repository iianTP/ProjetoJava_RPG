package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TheVoid {
	
	private BufferedImage[] images;
	
	private int imageNum;
	private int counter;

	public TheVoid() {
		
		this.images = new BufferedImage[5];
		this.imageNum = 1;

		for(int i = 1; i <= 5; i++) {
			
			try {
				this.images[i-1] = ImageIO.read(getClass().getResourceAsStream("/thevoid/background"+ i +".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void draw(Graphics2D brush) {
		
		if (this.counter > 10) {
			this.imageNum = (this.imageNum == 5) ? 1 : this.imageNum + 1;
			this.counter = 0;
		}
		brush.drawImage(images[imageNum-1], 0, 0, 720, 720, null);
		this.counter++;
		
	}

}
