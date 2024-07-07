package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidGameStateIndex;
import exceptions.InvalidProductSpecsException;
import exceptions.ItemAlreadyInStockException;
import items.*;
import main.screen.GameScreen;

public /*abstract*/ class Seller extends Npc{
	
	private Stock stock = new Stock();

	public Seller(int x, int y, GameScreen gs) {
		super(gs);
		
		super.setDirection("down");

		try {
			super.setX(x);
			super.setY(y);
		} catch (InvalidCoordinateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setSprites();
		setStock();
		
	}

	@Override
	public void action(Player player, Npc[] npcs) {
		
	}
	
	@Override
	public void interaction(Player player) {
		try {
			super.getGs().setGameState(6);
		} catch (InvalidGameStateIndex e) {
			e.printStackTrace();
		}
	}
	
	private void setStock() {
		try {
			this.stock.addItem(new Armor(1), 99, 10);
			this.stock.addItem(new Sword(2), 99, 10);
			this.stock.addItem(new Staff(6), 99, 10);
			this.stock.addItem(new Staff(5), 99, 10);
			this.stock.addItem(new Book(2), 99, 1);
		} catch (ItemAlreadyInStockException e) {
			e.printStackTrace();
		} catch (InvalidProductSpecsException e) {
			e.printStackTrace();
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
