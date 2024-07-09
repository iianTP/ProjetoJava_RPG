package entities.npcs.questNpc;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.Ghost;
import entities.npcs.Npc;
import entities.player.Player;
import exceptions.InvalidCoordinateException;
import exceptions.InventoryIsFullException;
import items.Armor;
import items.Item;
import main.screen.GameScreen;
import quests.KillEnemieQuest;
import quests.Quest;
import quests.Reward;

public class QTest extends QuestNpc {

	public QTest(int x, int y, GameScreen gs) {
		super(gs);
		
		super.setDirection("down");

		try {
			super.setX(x);
			super.setY(y);
		} catch (InvalidCoordinateException e) {
			e.printStackTrace();
		}
		
		setSprites();
		
		//super.setDialogue(new String[] {"gdsgdsd", "sdgshsrhse"});
		
		Reward reward = new Reward();
		reward.setGold(1);
		reward.setItem(new Armor(1));
		super.setQuest(new KillEnemieQuest(new Ghost(super.getGs()), 2, "MAIN", reward, this));
		
	}

	@Override
	public void setSprites() {
		try {
			
			super.setIdleSprites(
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleUp.png")),
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleDown.png")),
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleLeft.png")),
					ImageIO.read(getClass().getResourceAsStream("/warrior/WarriorIdleRight.png"))
			);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void action(Player player, Npc[] npcs) {
		
	}
	
	@Override
	public void interaction(Player player) {
		System.out.println("foi");

		if (super.isQuestRequested()) {
			
			Quest quest = player.getQuestList().getQuestByRequester(this);
			
			if (quest != null && quest.isDone() && !super.isRewarded()) {
				try {
					quest.redeemReward(player);
				} catch (InventoryIsFullException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			player.getQuestList().addQuest(super.getQuest());
			super.requestedQuest();
		}
		
	
	}

}
