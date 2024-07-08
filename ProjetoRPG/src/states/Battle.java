package states;

import combat.spells.Spell;
import entities.enemies.Enemie;
import entities.player.Player;
import entities.teammates.*;
import exceptions.InvalidTargetException;
import exceptions.IndexOutOfRangeException;
import items.Item;
import main.KeyInput;

public class Battle {
	
	private final Player player;
	private final Enemie enemie;
	private final Teammate[] teammates;
	private final Team[] team;
	private final KeyInput key;
	
	private String battleState = "choose-move";
	private int teammateIndex = 0;
	private int teamIndex = 0;

	private String message = "SEU TURNO";
	
	private String winner;
	
	private boolean battleEnded = false;
	
	private int selectedButton;
	
	private int inventoryPage = 1;
	
	private boolean changedBattleState;
	
	public Battle(Player player, Enemie enemie, KeyInput key, Teammate[] teammates) {
		this.player = player;
		this.enemie = enemie;
		this.teammates = teammates;
		this.team = new Team[] {player, teammates[0], teammates[1], teammates[2]};
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
			this.winner = "player";
		} 
		
		if (this.player.getStats().getHealth() <= 0) {
			this.message = "VOCE FOI DERROTADO";
			this.battleState = "ended";
			this.winner = "enemie";
		} 
		
		this.changedBattleState = false;
		
		if (this.key.isInteracting()) {
			
			this.changedBattleState = true;
			
			if (this.battleState.equals("enemie-turn")) {
				enemieTurn();
			}
			
			else if (this.battleState.equals("enemie-text")) {
				
				this.message = "SEU TURNO";
				if (this.enemie.getEffects().getCurrentEffect().equals("none")) {
					battleState = "choose-move";	
				} else {
					this.message = "O OPONENTE "+this.enemie.getEffects().getMessage();
					battleState = "enemie-effect";
				}
				
			}
			
			else if (this.battleState.equals("teammate-turn")) {
				
				if (this.teammates[this.teammateIndex].getStats() != null)
				this.teammateTurn();
				
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
				} catch (IndexOutOfRangeException e) {
					e.printStackTrace();
				}
			}
			
			else if (this.battleState.equals("enemie-effect")) {
				this.message = "SEU TURNO";
				battleState = "choose-move";
			}
			
			else if (this.battleState.equals("player-effect")) {
				this.message = "VOCE "+this.player.getEffects().getMessage();
				this.battleState = "teammate-turn";
			}

			else if (this.battleState.equals("teammate-effect")) {
				this.message = this.teammates[this.teammateIndex].getName() + " "
						+ this.teammates[this.teammateIndex].getEffects().getMessage();
				
				teammateIndex++;
				if (this.teammateIndex == 3) {
					battleState = "enemie-turn";
					this.teammateIndex = 0;
				} else {
					battleState = "teammate-turn";
				}
			}
			
			else if (this.battleState.equals("ended")) {
				this.battleEnded = true;
			}
				
		}
			
	}
	
	private void enemieTurn() {
		
		if (!this.enemie.getEffects().getCurrentEffect().equals("paralyzed")) {
			this.enemie.battleMove(this.team[this.teamIndex], this);
		}
		
		this.teamIndex++;
		if (this.teamIndex == 4) {
			
			this.enemie.getEffects().effect();
			battleState = "enemie-text";
			this.teamIndex = 0;
		} else {
			battleState = "enemie-turn";
		}
		
	}
	
	private void chooseMove() {
		
		switch (this.selectedButton) {
		case 0:
			try {
				this.player.attack(this.enemie);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
			
			this.message = "VOCE GOLPEOU SEU OPONENTE (-"+this.player.getStats().getStrenght()+"HP)";
			
			this.player.getEffects().effect();
			if (this.player.getEffects().getCurrentEffect().equals("none")) {
				this.battleState = "teammate-turn";
			} else {
				this.battleState = "player-effect";
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
			
			this.player.getEffects().effect();
			if (this.player.getEffects().getCurrentEffect().equals("none")) {
				this.battleState = "teammate-turn";
			} else {
				this.battleState = "player-effect";
			}
			
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
			Spell spell = this.player.getSpells().getSpell(this.selectedButton+1);
			if (spell != null) {
				try {
					this.player.magic(this.enemie, this.selectedButton);
					this.message = "VOCE USOU "+spell.getSpellName().toUpperCase();
					
					this.player.getEffects().effect();
					if (this.player.getEffects().getCurrentEffect().equals("none")) {
						this.battleState = "teammate-turn";
					} else {
						this.battleState = "player-effect";
					}
					
				} catch (InvalidTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void chooseItem() throws IndexOutOfRangeException {
		
		Item itemSelected;
		int itemIndex = this.selectedButton+4*(this.inventoryPage-1);
		
		if (itemIndex < 11 && this.selectedButton < 4) {
			itemSelected = this.player.getInventory().getItem(itemIndex);
			this.player.getInventory().removeItem(itemIndex);
			if (itemSelected != null) {
				if (itemSelected.isEquipable())
				this.player.equipItem(itemSelected);
				
				this.player.getEffects().effect();
				if (this.player.getEffects().getCurrentEffect().equals("none")) {
					this.battleState = "teammate-turn";
				} else {
					this.battleState = "player-effect";
				}
				
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
		
		this.teammates[this.teammateIndex].battleMove(this.enemie, this);
		
		this.teammates[this.teammateIndex].getEffects().effect();
		
		if (this.teammates[this.teammateIndex].getEffects().getCurrentEffect().equals("none")) {
			teammateIndex++;
			if (this.teammateIndex == 3) {
				battleState = "enemie-turn";
				this.teammateIndex = 0;
			}
		} else {
			this.battleState = "teammate-effect";
		}
		
		
	}
	
	public String getBattleState() {
		return battleState;
	}

	
	public void setMessage(String message) {
		this.message = message;
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

	public String getWinner() {
		return winner;
	}

	public boolean isChangedBattleState() {
		return changedBattleState;
	}
}