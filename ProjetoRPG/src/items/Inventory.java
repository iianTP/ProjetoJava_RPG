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
	public void addItem(Item item) throws InventoryIsFullException {
		if (isFull()) {
			throw new InventoryIsFullException();
		}
		
		super.addData(item);
		System.out.println("added");
		try {
			System.out.println(getItem(0).getName());
		} catch (IndexOutOfRangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if (this.getData(itemIndex) != null) {
			super.removeData(itemIndex);
		}
		System.out.println("removed");
	}
}
