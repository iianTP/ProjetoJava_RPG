package states;

import entities.Player;
import entities.enemies.Enemie;
import main.KeyInput;

public class Battle {
	
	private Player player;
	private Enemie enemie;
	private KeyInput key;
	private String battleState = "player-turn";
	
	public Battle(Player player, Enemie enemie, KeyInput key) {
		this.player = player;
		this.enemie = enemie;
		this.key = key;
	}
	
	public void combat() {

		if (enemie.getStats().getHealth() <= 0) {
			key.finishBattle();
			return;
		}
		
		if (key.isInteracting()) {
			
			switch (key.getCmdNum()) {
			case 0:
				
				if (this.battleState.equals("player-turn")) {
					enemie.takeDamage(player.getStats().getStrenght());
					this.battleState = "enemie-turn";
				} else {
					this.battleState = "player-turn";
				}
				
				break;
			case 1:

				if (this.battleState.equals("player-turn")) {
					this.battleState = "enemie-turn";
				} else {
					this.battleState = "player-turn";
				}
				
				break;
			case 2:

				if (this.battleState.equals("player-turn")) {
					this.battleState = "enemie-turn";
				} else {
					this.battleState = "player-turn";
				}
					
				break;
			case 3:
				
				if (this.battleState.equals("player-turn")) {
					this.battleState = "enemie-turn";
				} else {
					this.battleState = "player-turn";
				}
				
				break;
			case 4:
				key.finishBattle();
				break;
			}
				
		}
		
	}

	public String getBattleState() {
		return battleState;
	}
}