package entities.npcs.sellers;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.*;
import entities.enemies.Boss2;
import entities.enemies.Boss3;
import entities.player.Player;
import exceptions.IndexOutOfRangeException;
import exceptions.InvalidCoordinateException;
import exceptions.InvalidGameStateIndex;
import items.*;
import main.screen.GameScreen;
import quests.KillEnemieQuest;
import quests.Quest;
import quests.Reward;

public class LobbySeller extends Seller {
	
	private Quest mainQuest = new KillEnemieQuest(new Boss1(super.getGs()), 1,"main", new Reward(0), this);

	private int state = 0;
	
	private String[][] dialogue = {
			{
				"Ola, estranhos! O que fazem por aqui?",
				"Calma, voces nao parecem ser daqui.",
				"Bom, sejam bem vindos a esse mundo, sou apenas um simples vendedor, mas estarei disposto a ajudar voces com qualquer coisa.",
				"Seja em relacao a essas portas ou itens.",
				"Ou sair daqui...",
				"Sintam-se em casa!"
			},
			{
				"Voces querem sair daqui? Mas acabaram de chegar...",
				"Para a sorte de voces, eu sei exatamente como escapar desse lugar.",
				"Veem essa pedra azul no meu pescoco? Voces vao precisar trazer outras tres dessas.",
				"Sao fragmentos que um dia surgiram do nada, mas ninguem sabia o que eram.",
				"Mas eu descobri.",
				"O unico problema e que os outros pedacos estam em posse dos lideres dos tres mundos.",
				"Voces terao que tomar deles e trazer para ca, entao poderei juntar eles e voces vao poder voltar para casa.",
				"Qualquer coisa, eu tenho itens que poderao ajudar na jornada de voces, BOA SORTE!"
			},
			{
				"Otimo, conseguiram o primeiro pedaco.",
				"Voces devem ter enfrentado Maglorg, o lider de fogo.",
				"Ele era um cara muito cabeca dura e ranzinza.",
				"Foi ele o principal causador da grande guerra anos atras...",
				"Continuem, seu proximo destino e para aquela porta azul e verde.",
				"Inclusive, renovei o estoque da loja."
			},
			{
				"Muito bem, conseguiram mais um fragmento.",
				"O chefao daquele mundo esquisito era o pior de todos.",
				"Era um cara obsecado por perfeicao e por experimentacoes.",
				"Ele transformou quase todo seu corpo um maquina para poder ter mais controle de seus equipamentos.",
				"Conduzia testes bizarros com os habitantes...",
				"E estava sempre observando toda a regiao que governava, conferindo se tudo estava em \"Ordem\".",
				"O ultimo pedaco esta naquele mundo de nuvens, voces vao gostar de la.",
				"Inclusive, renovei o estoque da loja novamente."
			},
			{
				"Com esse, ja temos todos os pedacos.",
				"A lider do mundo das nuvens, ela cuidava bem dos habitantes de la, eu acho.",
				"Porem, era uma pessoa bastante egoista e odiava ser contrariada.",
				"Sempre achei todos eles muito estranhos...",
				"...",
				"Pronto, o artefato esta montado, quando estiverem prontos e so me chamar."
			},
			{
				"Prontos?",
				"La vamos nos!",
				"...",
				".....",
				".......",
				".........nao.",
				"LA VOU EU!"
			}
	};
	
	public LobbySeller(int x, int y, GameScreen gs) {
		super(gs);
		
		super.setDirection("down");
		
		super.setX(x);
		super.setY(y);
		
		setSprites();
		
		super.setLocation("lobby");
		
		super.addProduct(new Armor(1), 1, 5);
		super.addProduct(new Armor(2), 1, 10);
		super.addProduct(new Sword(1), 1, 5);
		super.addProduct(new Sword(2), 1, 10);
		super.addProduct(new Staff(1), 1, 5);
		super.addProduct(new Staff(2), 1, 10);
		super.addProduct(new Cloak(1), 1, 5);
		super.addProduct(new Cloak(2), 1, 10);
		
	}

	@Override
	public void setSprites() {
		try {
			super.setIdleSprites(
				ImageIO.read(getClass().getResourceAsStream("/npcs/lobbySeller/lobbySeller_up.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/lobbySeller/lobbySeller_down.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/lobbySeller/lobbySeller_left.png")), 
				ImageIO.read(getClass().getResourceAsStream("/npcs/lobbySeller/lobbySeller_right.png"))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void requestQuest(Player player) {
		player.getQuestList().addQuest(this.mainQuest);
	}
	
	private void openShop() {
		super.getGs().setShopState();
	}
	
	@Override
	public void action() {
		super.setSprite(super.getIdleSprites()[1]);
	}
	
	private void addStage2Products() {
		
		super.addProduct(new Cloak(3), 1, 50);
		super.addProduct(new Armor(3), 1, 50);
		super.addProduct(new Staff(3), 1, 50);
		super.addProduct(new Sword(4), 1, 100);
		
		for (int i = 0; i < 3; i++) {
			if (super.getStock().getItem(0) != null) {
				super.getStock().removeItem(0);
			} else {
				break;
			}
		}
		
	}
	
	private void addStage3Products() {
		
		super.addProduct(new Cloak(4), 1, 100);
		super.addProduct(new Armor(4), 1, 100);
		super.addProduct(new Staff(6), 1, 400);
		
		for (int i = 0; i < 5; i++) {
			if (super.getStock().getItem(0) != null) {
				super.getStock().removeItem(0);
			} else {
				break;
			}
		}
		
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
		
		switch (this.state) {
		case 0:
			super.setDialogue(this.dialogue[0]);
			super.getGs().setDialogueState();
			this.state++;
			break;
		case 1:
			super.setDialogue(this.dialogue[1]);
			super.getGs().setDialogueState();
			requestQuest(player);
			this.state++;
			break;
		case 2:
			if (this.mainQuest.isDone()) {
				player.getQuestList().removeQuest(this.mainQuest.getId());
				this.mainQuest = new KillEnemieQuest(new Boss2(super.getGs()), 1,"MAIN", new Reward(0), this);
				this.requestQuest(player);
				super.setDialogue(this.dialogue[2]);
				super.getGs().setDialogueState();
				this.state++;
				addStage2Products();
			} else {
				this.openShop();
			}
			break;
		case 3:
			if (this.mainQuest.isDone()) {
				player.getQuestList().removeQuest(this.mainQuest.getId());
				this.mainQuest = new KillEnemieQuest(new Boss3(super.getGs()), 1,"MAIN", new Reward(0), this);
				this.requestQuest(player);
				super.setDialogue(this.dialogue[3]);
				super.getGs().setDialogueState();
				this.state++;
				addStage3Products();
			} else {
				this.openShop();
			}
			break;
		case 4:
			if (this.mainQuest.isDone()) {
				player.getQuestList().removeQuest(this.mainQuest.getId());
				super.setDialogue(this.dialogue[4]);
				super.getGs().setDialogueState();
				this.state++;
			} else {
				this.openShop();
			}
			break;
		case 5:
			super.setDialogue(this.dialogue[5]);
			super.getGs().setDialogueState();
			player.addGameStage();
			break;
		}

	}

}
