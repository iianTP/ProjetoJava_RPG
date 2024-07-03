package items;

import exceptions.IndexOutOfRangeException;
import exceptions.InvalidProductSpecsException;
import exceptions.ItemAlreadyInStockException;
import main.DynamicArray;

public class Stock extends DynamicArray<Product>{
	
	public void addItem(Item item, int amount, int price) throws ItemAlreadyInStockException, InvalidProductSpecsException {
		
		if (amount < 0 || price < 0) {
			throw new InvalidProductSpecsException();
		}
		
		int i = 0;
		Product prod = super.getData(i);
		
		while (prod != null) {
			if (prod.getItem().getName().equals(item.getName())) {
				prod.increaseAmount();
				throw new ItemAlreadyInStockException("item já existe na loja");
			}
			i++;
			prod = super.getData(i);
		}
		
		super.addData(new Product(item, amount, price));
		
	}
	
	public Item getItem(int index) throws IndexOutOfRangeException {
		if (super.getData(index) == null) {
			throw new IndexOutOfRangeException();
		}
		return super.getData(index).getItem();
	}
	public Product getProduct(int index) {
		if (super.getData(index) == null) {
			return null;
		}
		return super.getData(index);
	}
	
	public void removeItem(int itemIndex) {
		
		super.removeData(itemIndex);
	}
	
	public int getAmount(int index) {
		return super.getData(index).getAmount();
	}

}
