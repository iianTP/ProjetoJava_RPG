package entities.npcs.sellers;

import entities.npcs.Npc;
import exceptions.InvalidProductSpecsException;
import exceptions.ItemAlreadyInStockException;
import items.*;
import main.screen.GameScreen;

public abstract class Seller extends Npc {
	
	private Stock stock = new Stock();

	public Seller(GameScreen gs) {
		super(gs);
	}

	public void addProduct(Item item, int amount, int price) {
		try {
			this.stock.addItem(item, amount, price);
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

}
