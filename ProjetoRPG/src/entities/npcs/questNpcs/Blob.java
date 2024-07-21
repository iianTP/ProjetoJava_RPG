package entities.npcs.questNpcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Potion;
import items.Staff;
import main.screen.GameScreen;
import quests.GetItemQuest;
import quests.Reward;

public class Blob extends QuestNpc {
	
	private String[][] dialogue = {
			{
				"Viajantes, viajantes! Voces poderiam me ajudar?",
				"Eu nao quero mais ser baixinho e preciso de uma POCAO DE FORCA",
				"Posso dar 10G e esse bastao em troca."
			},
			{
				"Oba, muito obrigado. Espero que isso funcione."
			},
			{
				"Viajantes, viajantes! Hmm voces parecem muito ocupados."
			},
	};

	public Blob(GameScreen gs) {
		super(gs);
		
		super.setX(41*48);
		super.setY(22*48);
		super.setLocation("world3");
		this.setSprites();
		
		super.setQuestDialogue(this.dialogue);
		super.setQuest(new GetItemQuest(new Potion(3), "SIDE", new Reward(new Staff(3),10)));
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/blob/blob_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/blob/blob_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/blob/blob_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/blob/blob_right.png"))
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
