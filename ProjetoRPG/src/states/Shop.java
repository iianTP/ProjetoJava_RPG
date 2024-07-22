package states;

import entities.npcs.sellers.Seller;
import entities.player.Player;
import exceptions.IndexOutOfRangeException;
import exceptions.InventoryIsFullException;
import items.Product;
import main.KeyInput;

public class Shop extends State {
	
	private KeyInput key;
	private Player player;
	private Seller seller;
	private int selectedButton = -1;

	private String shopState = "choose-act";

	public Shop(KeyInput key, Player player, Seller seller) {
		this.key = key;
		this.player = player;
		this.seller = seller;
	}

	public void shopCommands() {
		
		if (this.shopState.equals("choose-act")) {
			this.key.setMaxCmdNum(2);
		}
		
		else if (this.shopState.equals("choose-item")) {
			this.key.setMaxCmdNum((this.selectedButton > -1)? 1 : this.seller.getStock().getStockSize());
		}
		
		else if (this.shopState.equals("sell-item")) {
			this.key.setMaxCmdNum((this.selectedButton > -1)? 1 : this.player.getInventory().getItemQuantity());
		}
		
		if (this.key.isInteracting()) {
			
			if (this.shopState.equals("choose-act")) {
				
				if (this.key.getCmdNum() == 0) {
					
					this.shopState = "choose-item";
					this.key.setMaxCmdNum(this.seller.getStock().getStockSize());
					
				} else if (this.key.getCmdNum() == 1) {
					
					this.shopState = "sell-item";
					this.key.setMaxCmdNum(this.player.getInventory().getItemQuantity());
					
				} else if (this.key.getCmdNum() == 2) {
					super.setEnded(true);
				}
				this.key.resetCmdNum();
			}
			
			else if (this.shopState.equals("choose-item")) {
				
				if (this.selectedButton > -1) {
					this.buying();
				} else {
					if (this.key.getCmdNum() == this.seller.getStock().getStockSize()) {
						this.shopState = "choose-act";
					} else {
						this.selectedButton = this.key.getCmdNum();
					}
					
					this.key.resetCmdNum();
				}
				
				
			}
			
			else if (this.shopState.equals("sell-item")) {
				
				if (this.selectedButton > -1) {
					this.selling();
				} else {
					if (this.key.getCmdNum() == this.player.getInventory().getItemQuantity()) {
						this.shopState = "choose-act";
					} else {
						this.selectedButton = this.key.getCmdNum();
					}
					
					this.key.resetCmdNum();
				}
				
			}
			
		}
		
	}
	
	private void buying() {
		if (this.key.getCmdNum() == 0) {
			Product product = seller.getStock().getProduct(this.selectedButton);
			System.out.println(product.getItem().getName());
			
			if (this.player.getGold() >= product.getPrice() &&
				!this.player.getInventory().isFull()) {
				
				this.player.getInventory().addItem(product.getItem());
				
				this.player.reduceGold(product.getPrice());
				product.reduceAmount();
				
				if (product.getAmount() <= 0) {
					this.seller.getStock().removeItem(this.selectedButton);
				}
			}
		}
		this.selectedButton = -1;
		this.key.resetCmdNum();
	}
	
	private void selling() {
		if (this.key.getCmdNum() == 0) {
			try {
				this.player.getInventory().removeItem(this.selectedButton);
				this.player.addGold(10);
			} catch (IndexOutOfRangeException e) {
				e.printStackTrace();
			}
		} else {
			
		}
		this.selectedButton = -1;
		this.key.resetCmdNum();
	}
	
	public String getShopState() {
		return this.shopState;
	}

	public int getSelectedButton() {
		return selectedButton;
	}
	
}
