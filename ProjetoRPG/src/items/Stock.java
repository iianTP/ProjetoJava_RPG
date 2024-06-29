package items;

import main.DynamicArray;

public class Stock extends DynamicArray<Product>{
	
	public void addItem(Item item, int amount, int price) {
		
		int i = 0;
		Product prod = super.getData(i);
		while (prod != null) {
			
			if (prod.getItem().getName().equals(item.getName())) {
				prod.increaseAmount();
				break;
			}
			i++;
			prod = super.getData(i);
		}
		if (super.getData(i) == null) super.addData(new Product(item, amount, price));
	}
	
	public Item getItem(int index) {
		if (super.getData(index) == null) {
			return null;
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
