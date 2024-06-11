package states;

import java.util.Random;

import entities.Player;
import entities.enemies.Enemie;
import main.KeyInput;

public class Battle {
	
	private Player player;
	private Enemie enemie;
	private KeyInput key;
	private Random rng = new Random();
	
	private String[] battleCicle = {"player-turn", "enemie-turn", "enemie-text"};
	private String battleState = battleCicle[0];
	
	private int battleCicleIndex = 0;
	
	private String message = "SEU TURNO";
	
	public Battle(Player player, Enemie enemie, KeyInput key) {
		this.player = player;
		this.enemie = enemie;
		this.key = key;
	}
	
	public void combat() {

		if (this.key.isInteracting()) {
			
			if (this.battleState.equals("player-turn")) {
				
				switch (this.key.getCmdNum()) {
				case 0:
					this.player.attack(this.enemie);
					this.message = "VOCE GOLPEOU SEU OPONENTE (-"+this.player.getStats().getStrenght()+"HP)";
					this.proceedCicle();
					break;
				case 1:
					this.player.defend();
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
			
			else if (this.battleState.equals("enemie-turn")) {
				
				if (this.enemie.getStats().getHealth() <= 0) {
					this.key.finishBattle();
					return;
				}
			
				this.enemieMove();
				this.proceedCicle();
					
			}
			
			else if (this.battleState.equals("enemie-text")) {
				
				this.message = "SEU TURNO";
				this.proceedCicle();
				
			}
			
		}
		
		
	}
	
	public void enemieMove() {
		
		int randomNumber = this.rng.nextInt(this.enemie.getFullChance()) + 1;
		int playerHpBefore = this.player.getStats().getHealth();
		
		if (randomNumber <= this.enemie.getAttackChance()) {
			this.enemie.attack(player);
			int difference = this.player.getStats().getHealth() - playerHpBefore;
			this.message = "SEU OPONENTE LHE GOLPEOU ("+difference+"HP)";
		} else {
			this.enemie.defend();
			this.message = "SEU OPONENTE NAO FEZ NADA";
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