package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Armor;
import items.Stock;
import main.screen.GameScreen;

public class Seller extends Npc{
	
	private Stock stock = new Stock();

	public Seller(int x, int y, GameScreen gs) {
		super(gs);
		
		super.setDirection("down");

		super.setX(x);
		super.setY(y);
		super.setWalkSpeed(1);
		setSprites();
		setStock();
		
	}

	@Override
	public void action(Player player, Npc[] npcs) {
		
	}
	
	@Override
	public void interaction(Player player) {
		super.getGs().setGameState(6);
	}
	
	private void setStock() {
		for(int i = 0; i < 5; i++) {
			this.stock.addItem(new Armor(1), 1, 10);
		}
	}
	public Stock getStock() {
		return stock;
	}
	public boolean isOutOfStock() {
		return this.stock.getProduct(0) == null;
	}

	@Override
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

}
