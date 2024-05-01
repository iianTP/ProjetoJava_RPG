package Player;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyInput;

public class Warrior extends Player {

	public Warrior(KeyInput key) {
		super(key);	
		setSprites();
	}
	
	public void setSprites() {
		
		try {
			
			super.setIdleSprites(
					ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleUp.png")),
					ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleDown.png")),
					ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleLeft.png")),
					ImageIO.read(getClass().getResourceAsStream("/mage/MageIdleRight.png"))
			);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setStats() {
		
		super.setHealth(20);
		super.setMaxHealth(20);
		super.setMana(20);
		super.setMaxMana(20);
		
		super.setStrenght(5);
		super.setDefense(4);
		super.setMagic(1);
		super.setMagicDefense(1);
		
	}
	
}
