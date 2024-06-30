package states;

import entities.npcs.teammates.Teammate;
import entities.player.Player;
import exceptions.InventoryIndexOutOfRangeException;
import exceptions.InventoryIsFullException;
import items.Armor;
import items.Cloak;
import items.Item;
import items.Staff;
import items.Sword;
import main.KeyInput;

public class PlayerMenu {
	
	private String state = "main";
	private KeyInput key;
	private Player player;
	private Teammate[] teammates;
	
	private Item itemSelected;
	private int itemSelectedIndex = -1;
	
	public PlayerMenu(KeyInput key, Player player, Teammate[] teammates) {
		this.key = key;
		this.player = player;
		this.teammates = teammates;
	}
	
	public void playerMenu() {
		
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
				
				if (this.itemSelected == null) {
					chooseItem();
				} else {
					itemOptions();
				}
				
			} else if (this.state.equals("choose-character")) {
				try {
					chooseCharacter();
				} catch (InventoryIndexOutOfRangeException e) {
					e.printStackTrace();
				} catch (InventoryIsFullException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	private void chooseItem() {
			
		if (this.key.getCmdNum() == 10) {
			this.state = "main";
		} else {
			
			try {
				Item itemSelected = this.player.getInventory().getItem();
				this.itemSelectedIndex = this.key.getCmdNum();
				if (itemSelected != null) {
					this.itemSelected = itemSelected;
				}
			} catch (InventoryIndexOutOfRangeException e) {
				e.printStackTrace();
			}
			
		}
		this.key.resetCmdNum();
		
	}
	
	private void itemOptions() {
		if (this.key.getCmdNum() == 0) {
			
			this.state = "choose-character";
			
		} else if (this.key.getCmdNum() == 1) {
			
			try {
				this.player.getInventory().removeItem(this.itemSelectedIndex);
			} catch (InventoryIndexOutOfRangeException e) {
				e.printStackTrace();
			}
			this.itemSelected = null;
			
		} else if (this.key.getCmdNum() == 2) {
			this.itemSelected = null;
		}
	}
	
	private void chooseCharacter() throws InventoryIndexOutOfRangeException, InventoryIsFullException {
		
		int characterSelected = this.key.getCmdNum();
			
		switch (characterSelected) {
		case 0:
			if (this.itemSelected.checkRestriction(player)) {
				if (this.itemSelected.isEquipable()) {
					this.player.getInventory().removeItem(this.itemSelectedIndex);
					this.player.equipItem(itemSelected);
				}
			}
			break;
		case 1:
			if (this.itemSelected.checkRestriction(teammates[0])) {
				if (this.itemSelected.isEquipable()) {
					this.player.getInventory().removeItem(this.itemSelectedIndex);
					teammateEquipItem(teammates[0]);
				}
			}
			break;
		case 2:
			if (this.itemSelected.checkRestriction(teammates[1])) {
				if (this.itemSelected.isEquipable()) {
					this.player.getInventory().removeItem(this.itemSelectedIndex);
					teammateEquipItem(teammates[1]);
				}
			}
			break;
		case 3:
			if (this.itemSelected.checkRestriction(teammates[2])) {
				if (this.itemSelected.isEquipable()) {
					this.player.getInventory().removeItem(this.itemSelectedIndex);
					teammateEquipItem(teammates[2]);
				}
			}
			break;
		}
			
		this.itemSelected = null;
		this.itemSelectedIndex = -1;
		this.state = "stats";
		
	}
	
	private void teammateEquipItem(Teammate teammate) throws InventoryIsFullException {
		
		if (this.itemSelected instanceof Armor || this.itemSelected instanceof Cloak) {
			
			if (teammate.getArmorEquiped() != null) {
				this.player.getInventory().addItem(teammate.getArmorEquiped());
			}
			teammate.setArmorEquiped(this.itemSelected);
			
		} else if (this.itemSelected instanceof Sword || this.itemSelected instanceof Staff) {
			
			if (teammate.getWeaponEquiped() != null) {
				this.player.getInventory().addItem(teammate.getWeaponEquiped());
			}
			teammate.setWeaponEquiped(this.itemSelected);
			
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
