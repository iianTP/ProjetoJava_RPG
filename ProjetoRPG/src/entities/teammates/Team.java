package entities.teammates;

import entities.Battler;
import entities.enemies.Enemie;
import exceptions.InvalidStatsInputException;
import exceptions.InvalidTargetException;
import items.*;
import main.screen.GameScreen;
import states.battle.Battle;

public abstract class Team extends Battler {
	
	private Item armorEquiped;
	private Item weaponEquiped;
	private Inventory playerInventory;
	
	public Team(GameScreen gs) {
		super(gs);
	}
	
	@Override
	public <T extends Battler> void attack(T target, Battle battle) throws InvalidTargetException {
		if (target instanceof Enemie) {
			super.attack(target, battle);
		} else {
			throw new InvalidTargetException("alvo não é do tipo Enemie");
		}
	}
	
	@Override
	public <T extends Battler> void magic(T target, int spellId, Battle battle) throws InvalidTargetException {
		if (target instanceof Enemie) {
			super.magic(target, spellId, battle);
		} else {
			throw new InvalidTargetException("alvo não é do tipo Enemie");
		}
	}

	public void equipItem(Item item) {

		if (item instanceof Armor || item instanceof Cloak) {

			if (this.armorEquiped != null) {
				this.playerInventory.addItem(this.armorEquiped);
			}
			this.armorEquiped = item;
			try {
				if (item instanceof Armor) {
					super.getStats().setItemDefense(((Armor) item).getDefense());
				} else {
					super.getStats().setItemDefense(((Cloak) item).getDefense());
					super.getStats().setItemAgility(((Cloak) item).getAgility());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}

		} else if (item instanceof Sword || item instanceof Staff) {
			if (this.weaponEquiped != null) {
				this.playerInventory.addItem(this.weaponEquiped);
			}
			this.weaponEquiped = item;
			System.out.println(this.weaponEquiped.getShortName());
			try {
				if (item instanceof Sword) {
					super.getStats().setItemStrenght(((Sword) item).getStrength());
					super.getStats().setItemAgility(((Sword) item).getAgility());
				} else {
					super.getStats().setItemStrenght(((Staff) item).getStrength());
					super.getStats().setItemMagic(((Staff) item).getMagic());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}

		}

	}
	
	public void usePotion(Potion potion) {
		potion.consumePotion(super.getStats());
	}
	
	public void useBook(Book book, int slot) {
		book.readBook(super.getSpells(), slot);
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
