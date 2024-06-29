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
				
				this.enemieMove();
				battleState = "enemie-text";
					
			}
			
			else if (this.battleState.equals("enemie-text")) {
				
				this.message = "SEU TURNO";
				battleState = "choose-move";
				
			}

			else {
				
				switch (this.selectedButton) {
				
				case 0: // ATTACK
					
					
					if (this.battleState.equals("choose-move")) {
						
						this.player.attack(this.enemie);
						
						if (this.enemie.getStats().getHealth() <= 0) {
							this.message = "SEU OPONENTE FOI DERROTADO";
							this.battleEnded = true;
							return;
						} else {
							this.message = "VOCE GOLPEOU SEU OPONENTE (-"+this.player.getStats().getStrenght()+"HP)";
							this.battleState = "enemie-turn";
						}
						
					} else if (this.battleState.equals("choose-spell")) {
						
						this.player.magic(this.enemie, this.selectedButton);
						
					} else if (this.battleState.equals("choose-item")) {
						
					}
					
					break;
					
				case 1: // DEFENSE
					
					if (this.battleState.equals("choose-move")) {
						
						int playerHpBefore = this.player.getStats().getHealth();
						
						this.player.defend();
						
						int playerHpDifference = this.player.getStats().getHealth() - playerHpBefore;
						
						if (playerHpDifference > 0) {
							this.message = "VOCE RECUPEROU VIDA (+"+playerHpDifference+"HP)";
						} else {
							this.message = "VOCE NAO FEZ NADA";
						}
						
						this.battleState = "enemie-turn";
						
					} else if (this.battleState.equals("choose-spell")) {
						
						this.player.magic(this.enemie, this.selectedButton);
						
					} else if (this.battleState.equals("choose-item")) {
						
					}
					
					break;
					
				case 2: // MAGIC
					
					if (this.battleState.equals("choose-move")) {
						
						this.battleState = "choose-spell";
						
					} else if (this.battleState.equals("choose-spell")) {
						
						this.player.magic(this.enemie, this.selectedButton);
						
					} else if (this.battleState.equals("choose-item")) {
						
					}
					
					break;
					
				case 3: // INVENTORY
					
					if (this.battleState.equals("choose-move")) {
						
						this.battleState = "choose-item";
						
					} else if (this.battleState.equals("choose-spell")) {
						
						this.player.magic(this.enemie, this.selectedButton);
						
					} else if (this.battleState.equals("choose-item")) {
						
					}
					
					break;
					
				case 4: // SPECIAL
					
					if (this.battleState.equals("choose-move")) {
						
						this.battleState = "choose-special";
						
					} else if (this.battleState.equals("choose-spell")) {
						
						this.player.magic(this.enemie, this.selectedButton);
						
					} else if (this.battleState.equals("choose-item")) {
						
						this.battleState = "choose-move";
						
					}
					
					break;
					
				case 5: // FLEE
					
					if (this.battleState.equals("choose-move")) {
						
						this.battleEnded = true;
						this.key.resetCmdNum();
						
					} else if (this.battleState.equals("choose-spell")) {
						
						this.battleState = "choose-move";
						
					} else if (this.battleState.equals("choose-item")) {
						
						this.inventoryPage++;
						if (this.inventoryPage > 3) {
							this.inventoryPage = 1;
						}
						
					}
					
					return;
				}
				
			}
			
		}
		
		
	}
	
	public void enemieMove() {
		
		int randomNumber = this.enemie.enemieRng(this.enemie.getFullChance(), 1);
		int playerHpBefore = this.player.getStats().getHealth();
		int enemieHpBefore = this.enemie.getStats().getHealth();
		
		if (randomNumber <= this.enemie.getAttackChance()) {
			
			this.enemie.attack(player);
			int playerHpDifference = this.player.getStats().getHealth() - playerHpBefore;
			this.message = "SEU OPONENTE LHE GOLPEOU ("+playerHpDifference+"HP)";
			
		} else {
			
			this.enemie.defend();
			int enemieHpDifference = this.enemie.getStats().getHealth() - enemieHpBefore;
			if (enemieHpDifference > 0) {
				this.message = "SEU OPONENTE RECUPEROU VIDA (+"+enemieHpDifference+"HP)";
			} else {
				this.message = "SEU OPONENTE NAO FEZ NADA";
			}
			
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