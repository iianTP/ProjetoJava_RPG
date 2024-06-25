package items;

import exceptions.InventoryIndexOutOfRangeException;
import exceptions.InventoryIsFullException;
import main.DynamicArray;
import main.KeyInput;

public class Inventory extends DynamicArray<Item>{
	
	private KeyInput key;
	
	private Item armorEquiped;
	private Item weaponEquiped;
	private Item keyEquiped;
	
	public Inventory(KeyInput key) {
		this.key = key;
	}

	public boolean isFull() {
		return (super.getData(9) != null) ? true : false;
	}
	public void addItem(Item item) throws InventoryIsFullException {
		if (isFull()) {
			throw new InventoryIsFullException();
		} 
		super.addData(item);
	}
	
	
	public Item getItem() throws InventoryIndexOutOfRangeException {
		int itemIndex = this.key.getCmdNum();
		if (itemIndex < 0 || itemIndex > 9) {
			throw new InventoryIndexOutOfRangeException();
		}
		return super.getData(itemIndex);
	}
	
	public Item getItem(int index) throws InventoryIndexOutOfRangeException {
		if (index < 0 || index > 9) {
			throw new InventoryIndexOutOfRangeException();
		}
		return super.getData(index);
	}
	
	
	public void removeItem(int itemIndex) throws InventoryIndexOutOfRangeException {
		
		if (itemIndex < 0 || itemIndex > 9) {
			throw new InventoryIndexOutOfRangeException();
		}
		
		super.removeData(itemIndex);
		
	}

}
