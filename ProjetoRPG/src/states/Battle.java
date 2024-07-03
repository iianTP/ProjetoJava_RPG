package states;

import entities.enemies.Enemie;
import entities.player.Player;
import entities.teammates.*;
import exceptions.InventoryIndexOutOfRangeException;
import items.Item;
import main.KeyInput;

public class Battle {
	
	private final Player player;
	private final Enemie enemie;
	private final Teammate[] teammates;
	private final KeyInput key;
	
	private String battleState = "choose-move";
	private int teammateIndex = 0;

	private String message = "SEU TURNO";
	
	private boolean battleEnded = false;
	
	private int selectedButton;
	
	private int inventoryPage = 1;
	
	public Battle(Player player, Enemie enemie, KeyInput key, Teammate[] teammates) {
		this.player = player;
		this.enemie = enemie;
		this.teammates = teammates;
		this.key = key;
		this.key.resetCmdNum();
	}
	
	public void combat() {
		
		if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 5) {
			this.key.correctCmdNum();
		}

		this.selectedButton = this.key.getCmdNum();
		
		if (this.enemie.getStats().getHealth() <= 0) {
			this.message = "SEU OPONENTE FOI DERROTADO";
			this.battleState = "ended";
		} 
		
		if (this.key.isInteracting()) {
			
			if (this.battleState.equals("enemie-turn")) {
				enemieTurn();
			}
			
			else if (this.battleState.equals("enemie-text")) {
				
				this.message = "SEU TURNO";
				battleState = "choose-move";
				
			}
			
			else if (this.battleState.equals("teammate-turn")) {
				
				if (this.teammates[this.teammateIndex].getStats() != null)
				this.teammateTurn();
				
			}
			
			else if (this.battleState.equals("teammate-text")) {
				teammateIndex++;
				if (this.teammateIndex == 3) {
					battleState = "enemie-turn";
					this.teammateIndex = 0;
				} else {
					battleState = "teammate-turn";
				}
			}
			
			else if (this.battleState.equals("choose-move")) {
				this.chooseMove();
				if (this.selectedButton == 5) {
					return;
				}
			}
			
			else if (this.battleState.equals("choose-spell")) {
				this.chooseSpell();
			}
			
			else if (this.battleState.equals("choose-item")) {
				try {
					this.chooseItem();
				} catch (InventoryIndexOutOfRangeException e) {
					e.printStackTrace();
				}
			}
			
			else if (this.battleState.equals("ended")) {
				this.battleEnded = true;
			}
				
		}
			
	}
	
	private void enemieTurn() {
		
		int playerHpBefore = this.player.getStats().getHealth();
		int teammate1HpBefore = this.teammates[0].getStats().getHealth();
		int teammate2HpBefore = this.teammates[1].getStats().getHealth();
		int teammate3HpBefore = this.teammates[2].getStats().getHealth();
		
		int enemieHpBefore = this.enemie.getStats().getHealth();
		
		this.enemie.battleMove(this.player);
		this.enemie.battleMove(this.teammates[0]);
		this.enemie.battleMove(this.teammates[1]);
		this.enemie.battleMove(this.teammates[2]);
		
		if (this.player.getStats().getHealth() < playerHpBefore) {
			
			int playerHpDifference = this.player.getStats().getHealth() - playerHpBefore;
			int teammate1HpDifference = this.teammates[0].getStats().getHealth() - teammate1HpBefore;
			int teammate2HpDifference = this.teammates[1].getStats().getHealth() - teammate2HpBefore;
			int teammate3HpDifference = this.teammates[2].getStats().getHealth() - teammate3HpBefore;
			
			this.message = "SEU OPONENTE ATACOU ";
				/*	+ "J:("+playerHpDifference+"HP)"
					+ "T1:("+teammate1HpDifference+"HP)"
					+ "T2:("+teammate2HpDifference+"HP)"
					+ "T3:("+teammate3HpDifference+"HP)";
			*/
		} else {
			
			int enemieHpDifference = this.enemie.getStats().getHealth() - enemieHpBefore;
			if (enemieHpDifference > 0) {
				this.message = "SEU OPONENTE RECUPEROU VIDA (+"+enemieHpDifference+"HP)";
			} else {
				this.message = "SEU OPONENTE NAO FEZ NADA";
			}
			
		}
		battleState = "enemie-text";
		
	}
	
	private void chooseMove() {
		
		switch (this.selectedButton) {
		case 0:
			this.player.attack(this.enemie);
			
			this.message = "VOCE GOLPEOU SEU OPONENTE (-"+this.player.getStats().getStrenght()+"HP)";
			this.battleState = "teammate-turn";
			
			break;
		case 1:
			int playerHpBefore = this.player.getStats().getHealth();
			
			this.player.defend();
			
			int playerHpDifference = this.player.getStats().getHealth() - playerHpBefore;
			
			if (playerHpDifference > 0) {
				this.message = "VOCE RECUPEROU VIDA (+"+playerHpDifference+"HP)";
			} else {
				this.message = "VOCE NAO FEZ NADA";
			}
			this.battleState = "teammate-turn";
			break;
		case 2:
			this.battleState = "choose-spell";
			break;
		case 3:
			this.battleState = "choose-item";
			break;
		case 4:
			this.battleState = "choose-special";
			break;
		case 5:
			this.battleEnded = true;
			this.key.resetCmdNum();
			break;
		}
		
	}
	
	private void chooseSpell() {
		
		if (this.selectedButton == 5) {
			this.battleState = "choose-move";
		} else {
			this.player.magic(this.enemie, this.selectedButton);
		}
		
	}
	
	private void chooseItem() throws InventoryIndexOutOfRangeException {
		
		Item itemSelected;
		int itemIndex = this.selectedButton+4*(this.inventoryPage-1);
		
		if (itemIndex < 11 && this.selectedButton < 4) {
			 itemSelected = this.player.getInventory().getItem(itemIndex);
			 this.player.getInventory().removeItem(itemIndex);
			 if (itemSelected != null) {
				this.player.useItem(itemSelected);
				this.battleState = "teammate-turn";
			 }
		}
		
		else if (this.selectedButton == 4) {
			this.battleState = "choose-move";
		}
		
		else if (this.selectedButton ==  5) {
			this.inventoryPage++;
			if (this.inventoryPage > 3) {
				this.inventoryPage = 1;
			}
		}
		
	}
	
	private void teammateTurn() {
		
		String name = this.teammates[this.teammateIndex].getName();
		
		int teammateHpBefore = this.teammates[this.teammateIndex].getStats().getHealth();
		int enemieHpBefore = this.enemie.getStats().getHealth();
		
		this.teammates[this.teammateIndex].battleMove(this.enemie);
		
		if (this.enemie.getStats().getHealth() < enemieHpBefore) {
			
			int enemieHpDifference = this.enemie.getStats().getHealth() - enemieHpBefore;
			this.message = "O "+name+" GOLPEOU O INIMIGO ("+enemieHpDifference+"HP)";
			
		} else {
			
			int teammmateHpDifference = 
					this.teammates[this.teammateIndex].getStats().getHealth() - teammateHpBefore;
			
			if (teammmateHpDifference > 0) {
				this.message = "O "+name+" RECUPEROU VIDA (+"+teammmateHpDifference+"HP)";
			} else {
				this.message = "O "+name+" NAO FEZ NADA";
			}
			
		}
		
		teammateIndex++;
		if (this.teammateIndex == 3) {
			battleState = "enemie-turn";
			this.teammateIndex = 0;
		}
		
	}
	
	public String getBattleState() {
		return battleState;
	}

	public String getMessage() {
		return message;
	}

	public boolean isBattleEnded() {
		return battleEnded;
	}
	
	public int getInventoryPage() {
		return this.inventoryPage;
	}
}