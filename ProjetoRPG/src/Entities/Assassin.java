package Entities;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyInput;

public class Assassin extends Player {
	
	public Assassin(KeyInput key) {
		super(key);	
		setSprites();
	}
	
	public void setSprites() {
		
		try {
			
			super.setIdleSprites(ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleUp.png"))
								,ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleDown.png"))
								,ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleLeft.png"))
								,ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleRight.png")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setStats() {
		
	}

}
