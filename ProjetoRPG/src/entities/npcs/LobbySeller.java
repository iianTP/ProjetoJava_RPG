package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.*;
import entities.enemies.Boss2;
import entities.enemies.Boss3;
import entities.player.Player;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidGameStateIndex;
import items.*;
import main.screen.GameScreen;
import quests.KillEnemieQuest;
import quests.Quest;
import quests.Reward;

public class LobbySeller extends Seller {
	
	private boolean requestedQuest = false;
	
	private Quest mainQuest1 = new KillEnemieQuest(new Boss1(super.getGs()), 1,"main", new Reward(), this);
	private Quest mainQuest2 = new KillEnemieQuest(new Boss2(super.getGs()), 1,"main", new Reward(), this);
	private Quest mainQuest3 = new KillEnemieQuest(new Boss3(super.getGs()), 1,"main", new Reward(), this);

	public LobbySeller(int x, int y, GameScreen gs) {
		super(gs);
		
		super.setDirection("down");

		try {
			super.setX(x);
			super.setY(y);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}
		
		setSprites();
		
		super.setLocation("lobby");
		
		setStockProducts(new Armor(1), 2, 5);
		setStockProducts(new Sword(1), 2, 5);
		setStockProducts(new Staff(1), 2, 5);
		setStockProducts(new Cloak(1), 2, 5);
		
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/npcs/lobbySeller.png")));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void action(Player player, Npc[] npcs) {
		
	}
	
	private void setStockProducts(Item item, int amount, int price) {
		super.setStock(item, amount, price);
	}
	
	private void requestQuest(Player player) {
		player.getQuestList().addQuest(this.mainQuest1);
		this.requestedQuest = true;
	}
	
	@Override
	public void interaction(Player player) {
		System.out.println("foi");
		
		if (this.mainQuest1 != null) {
			if (!this.requestedQuest) {
				requestQuest(player);
			} else if (this.mainQuest1.isDone()){
				player.getQuestList().removeQuest(this.mainQuest1.getId());
				this.requestedQuest = false;
				this.mainQuest1 = this.mainQuest2;
				this.mainQuest2 = this.mainQuest3;
				this.mainQuest3 = null;
				if (this.mainQuest1 != null) {
					requestQuest(player);
				} else {
					System.out.println("GAME OVER");
				}
				
				
			} else {
				
				try {
					super.getGs().setGameState(6);
				} catch (InvalidGameStateIndex e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
			System.out.println("GAME OVER");
		}
		
	
	}

}
