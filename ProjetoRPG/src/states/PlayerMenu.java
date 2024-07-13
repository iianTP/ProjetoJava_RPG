package states;

import entities.player.Player;
import entities.teammates.Team;
import entities.teammates.Teammate;
import exceptions.IndexOutOfRangeException;
import items.Book;
import items.Item;
import items.Potion;
import main.KeyInput;

public class PlayerMenu {
	
	private String state = "main";
	private KeyInput key;
	private Player player;
	private Teammate[] teammates;
	private Team[] team;
	
	private Item itemSelected;
	private int itemSelectedIndex = -1;
	private int characterSelected;
	
	private boolean closedMenu = false;
	
	public PlayerMenu(KeyInput key, Player player, Teammate[] teammates) {
		this.key = key;
		this.player = player;
		this.teammates = teammates;
		team = new Team[] {this.player,this.teammates[0],this.teammates[1],this.teammates[2]};
	}
	
	public void playerMenu() {
		
		if (this.state.equals("main")) {
			this.key.setMaxCmdNum(3);
		} else if (this.state.equals("inventory")) {
			this.key.setMaxCmdNum(10);
		} else if (this.state.equals("choose-character")) {
			this.key.setMaxCmdNum(3);
		} else if (this.state.equals("choose-spellSlot")) {
			this.key.setMaxCmdNum(3);
		} else if (this.state.equals("quests")) {
			this.key.setMaxCmdNum(0);
		}
		
		if (this.key.isInteracting()) {
			
			if (this.state.equals("main")) {
				
				if (this.key.getCmdNum() == 0) {
					this.state = "stats";
				} else if (this.key.getCmdNum() == 1) {
					this.state = "inventory";
				} else if (this.key.getCmdNum() == 2) {
					this.state = "quests";
				}else if (this.key.getCmdNum() == 3) {
					this.closedMenu = true;
				}
				this.key.resetCmdNum();
				
			} else if (this.state.equals("stats")) {
				
				this.state = "main";
				this.key.resetCmdNum();
				
			} else if (this.state.equals("inventory")) {
				
				if (this.itemSelected == null) {
					chooseItem();
				} else {
					itemOptions();
				}
				
			} else if (this.state.equals("choose-character")) {
				try {
					chooseCharacter();
				} catch (IndexOutOfRangeException e) {
					e.printStackTrace();
				}
			} else if (this.state.equals("choose-spellSlot")) {
				chooseSpellSlot();
			}else if (this.state.equals("quests")) {
				this.state = "main";
				this.key.resetCmdNum();
			}
			
		}
		
	}
	
	private void chooseItem() {
			
		if (this.key.getCmdNum() == 10) {
			this.state = "main";
		} else {
			
			try {
				Item itemSelected = this.player.getInventory().getItem();
				this.itemSelectedIndex = this.key.getCmdNum();
				if (itemSelected != null) {
					this.itemSelected = itemSelected;
				}
			} catch (IndexOutOfRangeException e) {
				e.printStackTrace();
			}
			
		}
		this.key.resetCmdNum();
		
	}
	
	private void itemOptions() {
		if (this.key.getCmdNum() == 0) {
			
			this.state = "choose-character";
			
		} else if (this.key.getCmdNum() == 1) {
			
			try {
				this.player.getInventory().removeItem(this.itemSelectedIndex);
			} catch (IndexOutOfRangeException e) {
				e.printStackTrace();
			}
			this.itemSelected = null;
			
		} else if (this.key.getCmdNum() == 2) {
			this.itemSelected = null;
		}
	}
	
	private void chooseCharacter() throws IndexOutOfRangeException {
		
		this.characterSelected = this.key.getCmdNum();

		if (this.itemSelected.isEquipable()) {
			if (this.itemSelected.checkRestriction(team[characterSelected])) {
				this.player.getInventory().removeItem(this.itemSelectedIndex);
				team[characterSelected].equipItem(this.itemSelected);
			}
		} else if (this.itemSelected.isUsable()) {
			if (this.itemSelected instanceof Potion) {
				team[characterSelected].usePotion((Potion)this.itemSelected);
				try {
					this.player.getInventory().removeItem(this.itemSelectedIndex);
				} catch (IndexOutOfRangeException e) {
					e.printStackTrace();
				}
			} else if (this.itemSelected instanceof Book) {
				this.state = "choose-spellSlot";
				return;
			}
		}
		
		this.itemSelected = null;
		this.itemSelectedIndex = -1;
		this.state = "stats";
		
	}
	
	private void chooseSpellSlot() {
		
		this.team[this.characterSelected].useBook((Book)this.itemSelected,this.key.getCmdNum()+1);
		try {
			this.player.getInventory().removeItem(this.itemSelectedIndex);
		} catch (IndexOutOfRangeException e) {
			e.printStackTrace();
		}
		this.itemSelected = null;
		this.itemSelectedIndex = -1;
		this.state = "stats";
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return this.state;
	}
	public Item getItemSelected() {
		return this.itemSelected;
	}
	public int getItemSelectedIndex() {
		return this.itemSelectedIndex;
	}

	public boolean isClosedMenu() {
		return closedMenu;
	}

}
