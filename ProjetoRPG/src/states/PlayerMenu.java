package states;

import exceptions.InventoryIndexOutOfRangeException;
import items.Inventory;
import items.Item;
import main.KeyInput;

public class PlayerMenu {
	
	private String state = "main";
	private KeyInput key;
	
	private Item itemSelected;
	private int itemSelectedIndex;
	
	public PlayerMenu(KeyInput key) {
		this.key = key;
	}
	
	public void playerMenu(Inventory inventory) {
		
		if (this.key.isInteracting()) {
			
			
			if (this.state.equals("main")) {
				
				if (this.key.getCmdNum() == 0) {
					this.state = "stats";
				} else if (this.key.getCmdNum() == 1) {
					this.state = "inventory";
				}
				this.key.resetCmdNum();
				
			} else if (this.state.equals("stats")) {
				
				this.state = "main";
				this.key.resetCmdNum();
				
			} else if (this.state.equals("inventory")) {
				
				inventoryHandler(inventory);
				
			}
			
		}
		
	}
	
	public void inventoryHandler(Inventory inventory) {
		if (this.itemSelected == null) {
			
			if (this.key.getCmdNum() == 10) {
				this.state = "main";
			} else {
				
				try {
					Item itemSelected = inventory.getItem();
					this.itemSelectedIndex = this.key.getCmdNum();
					if (itemSelected != null) {
						this.itemSelected = itemSelected;
					}
				} catch (InventoryIndexOutOfRangeException e) {
					e.printStackTrace();
				}
				
			}
			this.key.resetCmdNum();
			
		} else {
			
			if (this.key.getCmdNum() == 0) {
				
			} else if (this.key.getCmdNum() == 1) {
				
				try {
					inventory.removeItem(this.itemSelectedIndex);
				} catch (InventoryIndexOutOfRangeException e) {
					e.printStackTrace();
				}
				
			} else if (this.key.getCmdNum() == 2) {
				this.itemSelected = null;
			}
			
		}
	}
	
	public String getState() {
		return this.state;
	}
	public Item getItemSelected() {
		return this.itemSelected;
	}
	public int getItemSelectedIndex() {
		return this.itemSelectedIndex;
	}

}
