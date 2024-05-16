package classes;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.Npc;
import entities.Player;
import main.GameScreen;
import main.KeyInput;

public class Healer extends Player{

	public Healer(KeyInput key, Npc[] npcs, GameScreen gs) {
		super(key, npcs, gs);	
		setSprites();
	}
	
	public void setSprites() {
		
		try {
			
			super.setIdleSprites(
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleUp.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleDown.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleLeft.png")),
					ImageIO.read(getClass().getResourceAsStream("/healer/HealerIdleRight.png"))
			);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*
	public void setStats() {
		
		super.setHealth(20);
		super.setMaxHealth(20);
		super.setMana(20);
		super.setMaxMana(20);
		
		super.setStrenght(1);
		super.setDefense(2);
		super.setMagic(4);
		super.setMagicDefense(5);
		
	}
	*/
}
