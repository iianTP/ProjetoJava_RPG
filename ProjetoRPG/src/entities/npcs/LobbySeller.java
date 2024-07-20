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
	
	private Quest mainQuest = new KillEnemieQuest(new Boss1(super.getGs()), 1,"main", new Reward(), this);

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
				"Continuem, seu proximo destino e para aquela porta azul e verde."
			},
			{
				"Muito bem, conseguiram mais um fragmento.",
				"O chefao daquele mundo esquisito era o pior de todos.",
				"Era um cara obsecado por perfeicao e por experimentacoes.",
				"Ele transformou quase todo seu corpo um maquina para poder ter mais controle de seus equipamentos.",
				"Conduzia testes bizarros com os habitantes...",
				"E estava sempre observando toda a regiao que governava, conferindo se tudo estava em \"Ordem\".",
				"O ultimo pedaco esta naquele mundo de nuvens, voces vao gostar de la."
			},
			{
				"Com esse, ja temos todos os pedacos.",
				"A lider do mundo das nuvens, ela cuidar bem dos habitantes de la.",
				"Porem, era uma pessoa bastante egoista e odiava ser contrariada.",
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
	
	private void dialogue() {
		super.getGs().setDialogueState();
	}
	
	private void requestQuest(Player player) {
		player.getQuestList().addQuest(this.mainQuest);
	}
	
	private void openShop() {
		super.getGs().setShopState();
	}
	
	@Override
	public void interaction(Player player) {
		System.out.println("foi");
		
		
		switch (this.state) {
		case 0:
			super.setDialogue(this.dialogue[0]);
			this.dialogue();
			this.state++;
			break;
		case 1:
			super.setDialogue(this.dialogue[1]);
			this.dialogue();
			requestQuest(player);
			this.state++;
			break;
		case 2:
			if (this.mainQuest.isDone()) {
				player.getQuestList().removeQuest(this.mainQuest.getId());
				this.mainQuest = new KillEnemieQuest(new Boss2(super.getGs()), 1,"MAIN", new Reward(), this);
				this.requestQuest(player);
				super.setDialogue(this.dialogue[2]);
				this.dialogue();
				this.state++;
			} else {
				this.openShop();
			}
			break;
		case 3:
			if (this.mainQuest.isDone()) {
				player.getQuestList().removeQuest(this.mainQuest.getId());
				this.mainQuest = new KillEnemieQuest(new Boss3(super.getGs()), 1,"MAIN", new Reward(), this);
				this.requestQuest(player);
				super.setDialogue(this.dialogue[3]);
				this.dialogue();
				this.state++;
			} else {
				this.openShop();
			}
			break;
		case 4:
			if (this.mainQuest.isDone()) {
				player.getQuestList().removeQuest(this.mainQuest.getId());
				super.setDialogue(this.dialogue[4]);
				this.dialogue();
				this.state++;
			}
			break;
		case 5:
			super.setDialogue(this.dialogue[5]);
			this.dialogue();
			break;
		}

	}

}
