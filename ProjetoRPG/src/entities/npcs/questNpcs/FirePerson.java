package entities.npcs.questNpcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import items.Cloak;
import main.screen.GameScreen;
import quests.GetItemQuest;
import quests.Reward;

public class FirePerson extends QuestNpc {
	
	private String[][] dialogue = {
			{
				"Ola viajantes, voces poderiam me fazer um favor?",
				"Eu adoraria comecar a estudar magia mas ainda nao tenho equipamento para isso.",
				"Voces poderiam trazer uma CAPA SIMPLES para mim, por favor?"
			},
			{
				"Voces trouxeram? Obrigada!",
				"Aqui esta sua recompensa."
			},
			{
				"Ola viajantes, voces poderiam me ajudar?",
				"Hmm, voces parecem muito ocupados agora, deixa para la..."
			}
	};

	public FirePerson(GameScreen gs) {
		super(gs);
		super.setX(34*48);
		super.setY(18*48);
		super.setLocation("world1");
		super.setQuestDialogue(this.dialogue);
		super.setQuest(new GetItemQuest(new Cloak(1), "SIDE", new Reward(20)));
		this.setSprites();
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/firePerson/firePerson_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/firePerson/firePerson_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/firePerson/firePerson_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/firePerson/firePerson_right.png"))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
		
		this.checkQuest(player);
		super.getGs().setDialogueState();
		
	}

}
