package entities.teammates;


import combat.BattleRng;
import entities.enemies.Enemie;
import exceptions.InvalidTargetException;
import interfaces.ICombat;
import main.screen.GameScreen;
import states.Battle;

public abstract class Teammate extends Team implements ICombat {
	
	private BattleRng battleRng = new BattleRng();
	
	public Teammate(GameScreen gs) {
		super(gs);
	}
	
	public void battleMove(Enemie enemie, Battle battle) {
		
		String move = this.battleRng.chooseMove();
		
		if (move.equals("attack")) {
			
			try {
				this.attack(enemie, battle);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
			
		} else if (move.equals("defense")) {
			super.defend(battle);
		} else {
			
			int spellId = this.battleRng.getRandomSpellId(super.getSpells(), super.getStats().getMana());
			if (spellId != -1) {
				try {
					this.magic(enemie, spellId-1, battle);
				} catch (InvalidTargetException e) {
					e.printStackTrace();
				}
			} else {
				battle.setMessage("O "+super.getName()+" TENTOU USAR UM FEITICO, MAS FALHOU");
			}
		}
	}
	
	public BattleRng getBattleRng() {
		return battleRng;
	}

}
