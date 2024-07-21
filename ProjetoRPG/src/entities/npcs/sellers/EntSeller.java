package entities.npcs.sellers;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Armor;
import items.Cloak;
import items.Staff;
import items.Sword;
import main.screen.GameScreen;

public class EntSeller extends Seller {

	public EntSeller(GameScreen gs) {
		super(gs);
		this.setSprites();
		
		super.addProduct(new Sword(5), 1, 200);
		super.addProduct(new Sword(6), 1, 300);
		super.addProduct(new Armor(6), 1, 300);
		super.addProduct(new Armor(5), 1, 200);
		super.addProduct(new Cloak(5), 1, 200);
		super.addProduct(new Cloak(6), 1, 300);
		super.addProduct(new Staff(5), 1, 200);
		
		super.setLocation("");
		
	}
	
	public void appear(String map) {
		super.setLocation(map);
		if (map.equals("world1")) {
			super.setX(17*48);
			super.setY(30*48);
		} else if (map.equals("world2")) {
			super.setX(50*48);
			super.setY(14*48-24);
		} else if (map.equals("world3")) {
			super.setX(31*48);
			super.setY(21*48);
		} else {
			super.setLocation("");
		}
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/npcs/entSeller.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interaction(Player player) {
		super.getGs().setShopState();
	}

}
