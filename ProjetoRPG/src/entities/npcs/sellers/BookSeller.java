package entities.npcs.sellers;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Book;
import main.screen.GameScreen;

public class BookSeller extends Seller {

	public BookSeller(GameScreen gs) {
		super(gs);
		
		super.addProduct(new Book(1), 5, 5);
		super.addProduct(new Book(2), 2, 20);
		super.addProduct(new Book(3), 3, 10);
		super.addProduct(new Book(4), 1, 15);
		super.addProduct(new Book(5), 3, 15);
		super.addProduct(new Book(6), 2, 20);
		super.addProduct(new Book(7), 2, 25);
		super.addProduct(new Book(8), 1, 30);
		super.addProduct(new Book(9), 1, 50);
		super.addProduct(new Book(10), 1, 100);
		
		super.setX(14*48);
		super.setY(35*48-24);
		
		this.setSprites();
		super.setLocation("world2");
		
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/npcs/bookSeller.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interaction(Player player) {
		super.getGs().setShopState();
	}

}
