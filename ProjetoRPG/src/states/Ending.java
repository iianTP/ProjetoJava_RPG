package states;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyInput;

public class Ending extends State {
	
	private String text = "...ele nos enganou.";
	
	private BufferedImage[] panels = new BufferedImage[7];
	
	private KeyInput key;
	
	private int endingIndex = -1;
	
	private int timer = 0;
	
	public Ending(KeyInput key) {
		
		this.setPanels();
		
		this.key = key;
		
	}
	
	public void ending() {
		this.timer++;
		if (this.timer == 300) {
			this.timer = 0;
			this.endingIndex++;
			if (this.endingIndex == 7) {
				super.setEnded(true);
			}
			System.out.println(this.endingIndex);
		}
	}
	
	private void setPanels() {
		for (int i = 0; i < this.panels.length; i++) {
			try {
				this.panels[i] = ImageIO.read(getClass().getResourceAsStream("/ending/sprite_"+i+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public BufferedImage getCurrentPanel() {
		return this.panels[this.endingIndex];
	}

	public int getEndingIndex() {
		return endingIndex;
	}

	public String getText() {
		return text;
	}

}
