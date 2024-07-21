package items;

import exceptions.IndexOutOfRangeException;
import exceptions.InventoryIsFullException;
import main.DynamicArray;
import main.KeyInput;

public class Inventory extends DynamicArray<Item>{
	
	private KeyInput key;
	
	public Inventory(KeyInput key) {
		this.key = key;
	}

	public boolean isFull() {
		return (super.getData(9) != null) ? true : false;
	}
	public void addItem(Item item) {
		if (isFull()) {
			try {
				throw new InventoryIsFullException();
			} catch (InventoryIsFullException e) {
				e.printStackTrace();
			}
		}
		
		super.addData(item);
	}
	
	
	public Item getItem() throws IndexOutOfRangeException {
		int itemIndex = this.key.getCmdNum();
		if (itemIndex < 0 || itemIndex > 9) {
			throw new IndexOutOfRangeException();
		}
		return super.getData(itemIndex);
	}
	
	public Item getItem(int index) throws IndexOutOfRangeException {
		if (index < 0 || index > 9) {
			throw new IndexOutOfRangeException();
		}
		return super.getData(index);
	}
	
	
	public void removeItem(int itemIndex) throws IndexOutOfRangeException {
		
		if (itemIndex < 0 || itemIndex > 9) {
			throw new IndexOutOfRangeException();
		}
		System.out.println("removed "+this.getData(itemIndex).getName());
		if (this.getData(itemIndex) != null) {
			super.removeData(itemIndex);
		}
		
	}
	
	public int getItemQuantity() {
		
		int count = 0;
		Item item;
		
		try {
			item = getItem(0);
			while (item != null) {
				count++;
				item = getItem(count);
			}
		} catch (IndexOutOfRangeException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
