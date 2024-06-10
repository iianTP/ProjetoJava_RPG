package states;

import entities.Player;
import entities.enemies.Enemie;
import main.KeyInput;

public class Battle {
	
	private Player player;
	private Enemie enemie;
	private KeyInput key;
	
	private String[] battleCicle = {"player-turn", "player-text", "enemie-turn"};
	private String battleState = battleCicle[0];
	
	private int battleCicleIndex = 0;
	
	private String message = "SEU TURNO";
	
	public Battle(Player player, Enemie enemie, KeyInput key) {
		this.player = player;
		this.enemie = enemie;
		this.key = key;
	}
	
	public void combat() {
		

		if (this.enemie.getStats().getHealth() <= 0) {
			this.key.finishBattle();
			return;
		}
		
		if (this.battleState.equals("player-turn") && this.key.isInteracting()) {
			
			switch (this.key.getCmdNum()) {
			case 0:
				this.enemie.takeDamage(player.getStats().getStrenght());
				this.message = "VOCE GOLPEOU SEU OPONENTE (-"+player.getStats().getStrenght()+"HP)";
				this.proceedCicle();
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				key.finishBattle();
				break;
			}
			
		}
		
		else if (this.battleState.equals("enemie-turn") && this.key.isInteracting()) {
			
			switch (this.key.getCmdNum()) {
			case 0:
				this.message = "SEU TURNO";
				this.proceedCicle();
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				this.key.finishBattle();
				break;
			}
				
		}
		
		else if (this.battleState.equals("player-text")) {
			if (this.key.isInteracting()) {
				this.message = "O OPONENTE NAO FEZ NADA";
				this.proceedCicle();
			}
		}
		
	}
	
	public void proceedCicle() {
		
		this.battleCicleIndex += (this.battleCicleIndex < 2) ? 1 : -2;
		this.battleState = this.battleCicle[this.battleCicleIndex];
		
	}

	public String getBattleState() {
		return battleState;
	}

	public String getMessage() {
		return message;
	}
}