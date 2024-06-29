package states;

import entities.npcs.Seller;
import entities.player.Player;
import exceptions.InventoryIsFullException;
import items.Product;
import main.KeyInput;

public class Shop {
	
	private KeyInput key;
	private Player player;
	private Seller seller;
	private int selectedButton;
	
	private String shopState = "choose-item";

	public Shop(KeyInput key, Player player, Seller seller) {
		this.key = key;
		this.player = player;
		this.seller = seller;
	}
	
	public void shopCommands() {
		
		if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 4) {
			this.key.correctCmdNum();
		}
		
		this.selectedButton = this.key.getCmdNum();
		
		if (this.key.isInteracting()) {
			
			if (this.shopState.equals("choose-item")) {
				
				this.shopState = "buying";
				
			} else if (this.shopState.equals("buying")) {
				
				switch (this.selectedButton) {
				case 0: // COMPRAR
					
					Product product = seller.getStock().getProduct(this.selectedButton);
					if (this.player.getGold() >= product.getPrice() &&
						!this.player.getInventory().isFull()) {
						
						try {
							this.player.getInventory().addItem(product.getItem());
						} catch (InventoryIsFullException e) {
							e.printStackTrace();
						}
						
						this.player.reduceGold(product.getPrice());
						product.reduceAmount();
						
						if (product.getAmount() <= 0) {
							this.seller.getStock().removeItem(this.selectedButton);
						}
						
						this.shopState = "choose-item";
						this.key.resetCmdNum();
					}
					
					break;
				case 1: // VOLTAR
					this.shopState = "choose-item";
					this.key.resetCmdNum();
					break;
				}
				
			}
			
		}
		
	}
	
	public String getShopState() {
		return this.shopState;
	}
	
}
