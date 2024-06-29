package items;

public class Product{
	
	private Item item;
	private int amount;
	private int price;
	
	public Product(Item item, int amount, int price) {
		this.item = item;
		this.amount = amount;
		this.price = price;
	}
	
	public void reduceAmount() {
		if (this.amount <= 0) {
			// exception
		}
		this.amount--;
	}
	public void increaseAmount() {
		this.amount++;
	}

	public Item getItem() {
		return item;
	}
	public int getAmount() {
		return amount;
	}
	public int getPrice() {
		return price;
	}

}
