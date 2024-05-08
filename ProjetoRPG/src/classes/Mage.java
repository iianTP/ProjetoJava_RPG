package classes;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Npc;
import entities.Player;
import main.KeyInput;

public class Mage extends Player {

	public Mage(KeyInput key, Npc[] npcs) {
		super(key, npcs);
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
			super.setWalkSprites();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setStats() {
		
		super.setHealth(20);
		super.setMaxHealth(20);
		super.setMana(20);
		super.setMaxMana(20);
		
		super.setStrenght(2);
		super.setDefense(3);
		super.setMagic(5);
		super.setMagicDefense(4);

	}
	

}
