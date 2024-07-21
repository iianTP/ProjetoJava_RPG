package entities.npcs.sellers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Potion;
import main.screen.GameScreen;

public class PotionSeller extends Seller {
	
	private BufferedImage table;

	public PotionSeller(GameScreen gs) {
		super(gs);
		
		super.addProduct(new Potion(1), 10, 5);
		super.addProduct(new Potion(2), 10, 5);
		super.addProduct(new Potion(3), 10, 5);
		super.addProduct(new Potion(4), 10, 5);
		super.addProduct(new Potion(5), 10, 5);
		
		super.setX(41*48);
		super.setY(19*48-24);

		super.setHitbox(new int[][] {{-48,0},{48*2,48*2}});
		
		super.setLocation("world1");
		this.setSprites();
		
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/npcs/potionSeller.png")));
			this.table = ImageIO.read(getClass().getResourceAsStream("/npcs/potionTable.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interaction(Player player) {
		if (player.getDirection().equals("up")) {
			super.getGs().setShopState();
		}
	}
	
	@Override
	public void draw(Graphics2D brush, Player player) {
		
		int x = super.getX() - player.getX() -48 + super.getGs().getScreenSide()/2;
		int y = super.getY() - player.getY() + super.getGs().getScreenSide()/2;
		
		super.draw(brush, player);
		
		if (player.getLocation().equals("world1")) {
			brush.drawImage(this.table, x, y, 48*3, 48*2, null);
		}
		
	}

}
