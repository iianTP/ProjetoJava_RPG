package entities.teammates;

import entities.Entity;
import exceptions.InventoryIsFullException;
import interfaces.ICombat;
import items.Armor;
import items.Cloak;
import items.Inventory;
import items.Item;
import items.Staff;
import items.Sword;
import main.screen.GameScreen;

public abstract class Team extends Entity implements ICombat {
	
	private Item armorEquiped;
	private Item weaponEquiped;
	private Inventory playerInventory;
	
	public Team(GameScreen gs) {
		super(gs);
	}
	
	public void useItem(Item item) {
		if (item.isEquipable()) {
			equipItem(item);
		} else if (item.isUsable()) {
			consumeItem();
		}
	}

	public void equipItem(Item item) {

		if (item instanceof Armor || item instanceof Cloak) {

			if (this.armorEquiped != null) {
				try {
					this.playerInventory.addItem(this.armorEquiped);
				} catch (InventoryIsFullException e) {
					e.printStackTrace();
				}
			}

			this.armorEquiped = item;

		} else if (item instanceof Sword || item instanceof Staff) {
			if (this.weaponEquiped != null) {
				try {
					this.playerInventory.addItem(this.armorEquiped);
				} catch (InventoryIsFullException e) {
					e.printStackTrace();
				}
			}

			this.weaponEquiped = item;

		}

	}
	
	private void consumeItem() {
		
	}
	
	
	
	public Item getArmorEquiped() {
		return armorEquiped;
	}
	public Item getWeaponEquiped() {
		return weaponEquiped;
	}
	public void setArmorEquiped(Item armorEquiped) {
		this.armorEquiped = armorEquiped;
	}
	public void setWeaponEquiped(Item weaponEquiped) {
		this.weaponEquiped = weaponEquiped;
	}

	public void setPlayerInventory(Inventory playerInventory) {
		this.playerInventory = playerInventory;
	}

}
