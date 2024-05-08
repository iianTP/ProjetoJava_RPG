package classes;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Npc;
import entities.Player;
import main.KeyInput;

public class Assassin extends Player {
	
	public Assassin(KeyInput key, Npc[] npcs) {
		super(key, npcs);
		setSprites();
	}
	
	public void setSprites() {
		
		try {
			
			super.setIdleSprites(
					ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleUp.png")),
					ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleDown.png")),
					ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleLeft.png")),
					ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleRight.png"))
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
		
		super.setStrenght(3);
		super.setDefense(5);
		super.setMagic(2);
		super.setMagicDefense(2);
		
	}

}
