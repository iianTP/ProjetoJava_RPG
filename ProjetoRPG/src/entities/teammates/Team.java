package entities.teammates;

import combat.Effects;
import combat.Spells;
import entities.Entity;
import entities.Stats;
import exceptions.InvalidStatsInputException;
import exceptions.InventoryIsFullException;
import interfaces.ICombat;
import items.*;
import main.screen.GameScreen;

public abstract class Team extends Entity implements ICombat {
	
	public Effects getEffects() {
		return effects;
	}

	private Item armorEquiped;
	private Item weaponEquiped;
	private Inventory playerInventory;
	
	private Stats stats;
	
	private Effects effects = new Effects(this.stats);
	private Spells spells = new Spells(this.effects);
	
	private int potionEffectCounter = 0;
	private Potion potion;
	
	public Team(GameScreen gs) {
		super(gs);
	}
	
	public void useItem(Item item) {
		if (item.isEquipable()) {
			equipItem(item);
		} else if (item.isUsable()) {
			System.out.println("isUsable");
			consumeItem(item);
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
			
			try {
				this.armorEquiped = item;
				if (item instanceof Armor) {
					this.stats.setItemDefense(((Armor) item).getDefense());
				} else {
				//	this.stats.setItemDefense(((Cloak) item).getDefense());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}

		} else if (item instanceof Sword || item instanceof Staff) {
			if (this.weaponEquiped != null) {
				try {
					this.playerInventory.addItem(this.armorEquiped);
				} catch (InventoryIsFullException e) {
					e.printStackTrace();
				}
			}

			//try {
				this.weaponEquiped = item;
			/*	if (item instanceof Sword) {
					this.stats.setItemDefense(((Sword) item).getStrength());
				} else {
					this.stats.setItemDefense(((Cloak) item).getDefense());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}*/

		}

	}
	
	private void consumeItem(Item item) {
		if (item instanceof Potion || item instanceof Book) {
			if (item instanceof Potion) {
				System.out.println("found potion");
				this.potion = (Potion) item;
				this.potion.consumePotion(stats);
				
				if (this.potion.getType() > 2) {
					this.potionEffectCounter++;
				}
				
			}
		}
	}
	
	public void proceedPotionCounter() {
		this.potionEffectCounter++;
		if (this.potionEffectCounter == 6) {
			this.potion.stopEffect(this.stats);;
			this.potion = null;
		}
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

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Spells getSpells() {
		return spells;
	}

}
