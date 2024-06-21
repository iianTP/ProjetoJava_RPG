package items;

import exceptions.InventoryIndexOutOfRangeException;
import exceptions.InventoryIsFullException;
import main.DynamicArray;

public class Inventory extends DynamicArray<Item>{

	public boolean isFull() {
		return (super.getData(9) != null) ? true : false;
	}
	public void addItem(Item item) throws InventoryIsFullException {
		if (super.getData(9) != null) {
			throw new InventoryIsFullException();
		} 
		super.addData(item);
	}
	public Item getItem(int index) throws InventoryIndexOutOfRangeException {
		if (index < 0 || index > 9) {
			throw new InventoryIndexOutOfRangeException();
		}
		return super.getData(index);
	}
	

}
