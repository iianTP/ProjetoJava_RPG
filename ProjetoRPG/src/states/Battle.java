package states;

import combat.spells.Spell;
import entities.enemies.Enemie;
import entities.player.Player;
import entities.teammates.*;
import exceptions.InvalidTargetException;
import exceptions.IndexOutOfRangeException;
import items.Book;
import items.Item;
import items.Potion;
import main.KeyInput;

public class Battle extends State {
	
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
	
	private int selectedButton;
	
	private int inventoryPage = 1;
	
	private Item itemSelected;
	public Item getItemSelected() {
		return itemSelected;
	}

	private Team selectedCharacter;
	
	public Battle(Player player, Enemie enemie, KeyInput key, Teammate[] teammates) {
		this.player = player;
		this.enemie = enemie;
		this.teammates = teammates;
		this.team = new Team[] {player, teammates[0], teammates[1], teammates[2]};
		this.key = key;
		this.key.resetCmdNum();
	}
	
	private void checkDeadBattlers() {
		
		if (this.enemie.getStats().getHealth() <= 0) {
			this.message = "SEU OPONENTE FOI DERROTADO (+"+this.enemie.getGold()+"G +"+this.enemie.getExperience()+"XP)";
			this.battleState = "ended";
			this.winner = "player";
		} 
		
		if (this.player.getStats().getHealth() <= 0) {
			this.message = "VOCE FOI DERROTADO";
			this.battleState = "ended";
			this.winner = "enemie";
		} 
		
		if (this.teammates[this.teammateIndex].getStats().getHealth() <= 0) {
			this.teammateIndex++;
			if (this.teammateIndex == 3) {
				this.teammateIndex = 0;
				this.battleState = "enemie-turn";
			}
		}
		
		if (this.team[this.teamIndex].getStats().getHealth() <= 0) {
			this.teamIndex++;
			if (this.teamIndex == 4) {
				this.teamIndex = 0;
				this.battleState = "choose-move";
			}
		}
		
	}
	
	private void getLoot() {
		this.player.addExperience(this.enemie.getExperience());
		this.player.addGold(this.enemie.getGold());
	}
	
	private void setBattleButtonsConfig() {
		if (this.itemSelected != null && this.battleState.equals("choose-item")) {
			this.key.setButtonCols(4);
			this.key.setMaxCmdNum(3);
		} else {
			this.key.setButtonCols(2);
			this.key.setMaxCmdNum(5);
		}
	}
	
	private void checkParalyzedBattlers() {
		
		String pCurrentEffect = this.player.getEffects().getCurrentEffect();
		String eCurrentEffect = this.enemie.getEffects().getCurrentEffect();
		String tCurrentEffect = this.teammates[this.teammateIndex].getEffects().getCurrentEffect();
		
		if (this.battleState.equals("choose-move") && pCurrentEffect.equals("paralyzed")) {
			
			this.player.getEffects().effect();
			this.battleState = "player-effect";
			
		} else if (this.battleState.equals("enemie-turn") && eCurrentEffect.equals("paralyzed")) {
			
			this.enemie.getEffects().effect();
			battleState = "enemie-text";
			
		} else if (this.battleState.equals("teammate-turn") && tCurrentEffect.equals("paralyzed")) {
			
			this.teammates[this.teammateIndex].getEffects().effect();
			this.battleState = "teammate-effect";
			
		}
		
	}
	
	public void combat() {
		
		this.setBattleButtonsConfig();
		
		this.setItemText();
		this.setSpellText();
		
		this.selectedButton = this.key.getCmdNum();
		
		this.checkDeadBattlers();
		
		this.checkParalyzedBattlers();
		
		super.setStateChanged(false); 
		
		if (this.key.isInteracting()) {
			
			super.setStateChanged(true);
			
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
				
				if (itemSelected != null) {
					this.key.setMaxCmdNum(3);
					this.chooseCharacter();
				} else {
					try {
						this.chooseItem();
					} catch (IndexOutOfRangeException e) {
						e.printStackTrace();
					}
				}
				
			}
			
			else if (this.battleState.equals("choose-spellSlot")) {
				this.chooseSpellSlot();
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
					this.battleState = "enemie-turn";
					this.teammateIndex = 0;
				} else {
					this.battleState = "teammate-turn";
				}
			}
			
			else if (this.battleState.equals("ended")) {
				super.setEnded(true);
				if (this.winner.equals("player")) {
					this.getLoot();
					while (this.player.getExperience() >= this.player.getMaxExperience()) {
						this.levelUp();
					}
				}
			}
			
		}
		
			
	}
	
	public void levelUp() {
		
		this.player.levelUp();
		
		this.player.getStats().buffStats();
		this.teammates[0].getStats().buffStats();
		this.teammates[1].getStats().buffStats();
		this.teammates[2].getStats().buffStats();
		
	}
	
	private void chooseMove() {
		
		switch (this.selectedButton) {
		case 0:
			try {
				this.player.attack(this.enemie, this);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
			
			this.player.getEffects().effect();
			if (this.player.getEffects().getCurrentEffect().equals("none")) {
				this.battleState = "teammate-turn";
			} else {
				this.battleState = "player-effect";
			}
			this.player.getStats().overdriveCountdown();
			this.player.getStats().potionCountdown();
			
			break;
		case 1:
			
			this.player.defend(this);
			
			this.player.getEffects().effect();
			if (this.player.getEffects().getCurrentEffect().equals("none")) {
				this.battleState = "teammate-turn";
			} else {
				this.battleState = "player-effect";
			}
			this.player.getStats().overdriveCountdown();
			this.player.getStats().potionCountdown();
			break;
		case 2:
			this.battleState = "choose-spell";
			break;
		case 3:
			this.battleState = "choose-item";
			break;
		case 4:
			if (this.player.getStats().getOverdrive() == 100) {
				
				this.player.getStats().overdriveCountdown();
				this.player.getStats().potionCountdown();
				
				this.player.special(this.team, this);
				
				this.player.getEffects().effect();
				if (this.player.getEffects().getCurrentEffect().equals("none")) {
					this.battleState = "teammate-turn";
				} else {
					this.battleState = "player-effect";
				}
				
			}
			break;
		case 5:
			super.setEnded(true);
			this.key.resetCmdNum();
			break;
		}
		
	}
	
	private void chooseSpell() {
		
		if (this.selectedButton == 5) {
			this.battleState = "choose-move";
		} else {
			try {
				Spell spell = this.player.getSpells().getSpell(this.selectedButton+1);
				if (spell != null && this.player.getStats().getMana() >= -1*spell.getManaCost()) {
					this.player.magic(this.enemie, this.selectedButton, this);
					
					this.player.getEffects().effect();
					if (this.player.getEffects().getCurrentEffect().equals("none")) {
						this.battleState = "teammate-turn";
					} else {
						this.battleState = "player-effect";
					}
					this.player.getStats().overdriveCountdown();
					this.player.getStats().potionCountdown();
				}
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void chooseItem() throws IndexOutOfRangeException {
		
		int itemIndex = this.selectedButton+4*(this.inventoryPage-1);
		
		if (itemIndex < 11 && this.selectedButton < 4) {
			this.itemSelected = this.player.getInventory().getItem(itemIndex);
			this.player.getInventory().removeItem(itemIndex);
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
	
	private void chooseCharacter() {
		
		selectedCharacter = null;
		
		switch (this.selectedButton) {
		case 0:
			selectedCharacter = teammates[0];
			break;
		case 1:
			selectedCharacter = this.player;
			break;
		case 2:
			selectedCharacter = teammates[1];
			break;
		case 3:
			selectedCharacter = teammates[2];
			break;
		}
		
		if (itemSelected.isEquipable()) {
			
			if (this.itemSelected.checkRestriction(this.selectedCharacter)) {
				this.selectedCharacter.equipItem(itemSelected);
				
				this.message = this.selectedCharacter.getName()+" EQUIPOU "+this.itemSelected.getName();
				this.itemSelected = null;
				
				this.player.getEffects().effect();
				if (this.player.getEffects().getCurrentEffect().equals("none")) {
					this.battleState = "teammate-turn";
				} else {
					this.battleState = "player-effect";
				}
				this.player.getStats().overdriveCountdown();
				this.player.getStats().potionCountdown();
			}
			
		} else if (itemSelected.isUsable()) {
			if (this.itemSelected instanceof Potion) {
				
				selectedCharacter.usePotion((Potion)this.itemSelected);
				
				this.message = this.selectedCharacter.getName()+" USOU "+this.itemSelected.getName();
				this.itemSelected = null;
				
				this.player.getEffects().effect();
				if (this.player.getEffects().getCurrentEffect().equals("none")) {
					this.battleState = "teammate-turn";
				} else {
					this.battleState = "player-effect";
				}
				this.player.getStats().overdriveCountdown();
				this.player.getStats().potionCountdown();
				
			} else if (this.itemSelected instanceof Book) {
				this.battleState = "choose-spellSlot";
			}
		}
		
	}
	
	private void chooseSpellSlot() {
		
		if (this.key.getCmdNum() == 5) {
			this.battleState = "choose-item";
		} else {
			this.selectedCharacter.useBook((Book)this.itemSelected,this.selectedButton+1);
			
			this.message = this.selectedCharacter.getName()+" APRENDEU "+this.itemSelected.getName();
			this.itemSelected = null;
			
			this.player.getEffects().effect();
			if (this.player.getEffects().getCurrentEffect().equals("none")) {
				this.battleState = "teammate-turn";
			} else {
				this.battleState = "player-effect";
			}
			this.player.getStats().overdriveCountdown();
			this.player.getStats().potionCountdown();
		}
		
	}
	
	private void enemieTurn() {
		
		if (!this.enemie.getEffects().getCurrentEffect().equals("paralyzed") && 
				this.team[this.teamIndex].getStats().getHealth() > 0) {
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
		this.teammates[this.teammateIndex].getStats().overdriveCountdown();
		this.teammates[this.teammateIndex].getStats().potionCountdown();
		
	}
	
	private void setItemText() {
		if (this.battleState.equals("choose-item") && this.itemSelected == null) {
			int index = this.selectedButton + 4*(this.inventoryPage-1);
			try {
				if (index < 10) {
					Item item = this.player.getInventory().getItem(index);
					this.message = (item != null) ? item.getName()+": "+item.getDescription():"-";
				}
			} catch (IndexOutOfRangeException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setSpellText() {
		if (this.battleState.equals("choose-spell")) {
			int index = this.selectedButton+1;
			if (index < 5) {
				Spell spell = this.player.getSpells().getSpell(index);
				this.message = (spell != null) ? spell.getSpellName()+": description":"-";
			}
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

	public int getInventoryPage() {
		return this.inventoryPage;
	}

	public String getWinner() {
		return winner;
	}

}