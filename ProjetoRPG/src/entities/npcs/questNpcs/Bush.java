package entities.npcs.questNpcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Book;
import main.screen.GameScreen;
import quests.GetItemQuest;
import quests.Reward;

public class Bush extends QuestNpc {
	
	private String[][] dialogue = {
			{
				"Ola, nunca vi voces por aqui antes, sejam bem vindos!",
				"Viajantes, voces poderiam me fazer um favor?",
				"Eu estava precisando de um livro de HIPNOSE. Voces poderiam conseguir um para mim?"
			},
			{
				"Conseguiram? Muito obrigado."
			},
			{
				"Ola, nunca vi voces por aqui antes, sejam bem vindos!",
				"Viajantes, voces poderiam me fazer um fav-",
				"Voces parecem muito ocupados, deixa para a proxima."
			}
	};

	public Bush(GameScreen gs) {
		super(gs);
		
		super.setX(23*48);
		super.setY(22*48);
		
		super.setLocation("world3");
		
		this.setSprites();
		super.setQuestDialogue(this.dialogue);
		super.setQuest(new GetItemQuest(new Book(3), "SIDE", new Reward(50)));
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/bush/bush_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/bush/bush_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/bush/bush_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/bush/bush_right.png"))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void action() {
		super.setSprite(super.getIdleSprites()[1]);
	}

	@Override
	public void interaction(Player player) {
		
		if (player.getDirection().equals("down")) {
			super.setSprite(super.getIdleSprites()[0]);
		} else if (player.getDirection().equals("left")) {
			super.setSprite(super.getIdleSprites()[3]);
		} else if (player.getDirection().equals("right")) {
			super.setSprite(super.getIdleSprites()[2]);
		}
		
		super.checkQuest(player);
		super.getGs().setDialogueState();
	}

}
