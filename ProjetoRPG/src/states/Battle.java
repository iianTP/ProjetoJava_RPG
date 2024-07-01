package states;

import entities.enemies.Enemie;
import entities.player.Player;
import main.KeyInput;

public class Battle {
	
	private final Player player;
	private final Enemie enemie;
	private final KeyInput key;
	
	private String battleState = "choose-move";

	private String message = "SEU TURNO";
	
	private boolean battleEnded = false;
	
	private int selectedButton;
	
	private int inventoryPage = 1;
	
	public Battle(Player player, Enemie enemie, KeyInput key) {
		this.player = player;
		this.enemie = enemie;
		this.key = key;
		this.key.resetCmdNum();
	}
	
	public void combat() {
		
		if (this.key.getCmdNum() < 0 || this.key.getCmdNum() > 5) {
			this.key.correctCmdNum();
		}

		this.selectedButton = this.key.getCmdNum();
		
		if (this.key.isInteracting()) {
			
			if (this.battleState.equals("enemie-turn")) {
				enemieTurn();
			} else if (this.battleState.equals("enemie-text")) {
				
				this.message = "SEU TURNO";
				battleState = "choose-move";
				
			} else if (this.battleState.equals("choose-move")) {
				this.chooseMove();
				if (this.selectedButton == 5) {
					return;
				}
			}  else if (this.battleState.equals("choose-spell")) {
				this.chooseSpell();
			} else if (this.battleState.equals("choose-item")) {
				this.chooseItem();
			}
				
		}
			
	}
	
	private void enemieTurn() {
		
		int playerHpBefore = this.player.getStats().getHealth();
		int enemieHpBefore = this.enemie.getStats().getHealth();
		
		this.enemie.battleMove(this.player);
		
		if (this.player.getStats().getHealth() < playerHpBefore) {
			
			int playerHpDifference = this.player.getStats().getHealth() - playerHpBefore;
			this.message = "SEU OPONENTE LHE GOLPEOU ("+playerHpDifference+"HP)";
			
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
			
			if (this.enemie.getStats().getHealth() <= 0) {
				this.message = "SEU OPONENTE FOI DERROTADO";
				this.battleEnded = true;
				return;
			} else {
				this.message = "VOCE GOLPEOU SEU OPONENTE (-"+this.player.getStats().getStrenght()+"HP)";
				this.battleState = "enemie-turn";
			}
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
			this.battleState = "enemie-turn";
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
	
	private void chooseItem() {
		
		switch (this.selectedButton) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			this.battleState = "choose-move";
			break;
		case 5:
			this.inventoryPage++;
			if (this.inventoryPage > 3) {
				this.inventoryPage = 1;
			}
			break;
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