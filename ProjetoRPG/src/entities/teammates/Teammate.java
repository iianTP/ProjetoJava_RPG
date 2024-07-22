package entities.teammates;


import entities.Battler;
import exceptions.InvalidTargetException;
import interfaces.ICombat;
import main.screen.GameScreen;
import states.battle.Battle;
import states.battle.BattleRng;

public abstract class Teammate extends Team implements ICombat {
	
	private BattleRng battleRng = new BattleRng(super.getRng());
	
	public Teammate(GameScreen gs) {
		super(gs);
	}
	
	public void battleMove(Battler target, Battle battle) {
		
		String move = this.battleRng.chooseMove(super.getStats().getOverdrive());
		
		if (move.equals("attack")) {
			
			try {
				this.attack(target, battle);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
			
		} else if (move.equals("defense")) {
			super.defend(battle);
		} else  if (move.equals("magic")) {
			
			int spellId = this.battleRng.getRandomSpellId(super.getSpells(), super.getStats().getMana());
			if (spellId != -1) {
				try {
					this.magic(target, spellId-1, battle);
				} catch (InvalidTargetException e) {
					e.printStackTrace();
				}
			} else {
				battle.setMessage("O "+super.getName()+" TENTOU USAR UM FEITICO, MAS FALHOU");
			} 
			
		} else {
			this.special(target, battle);
		}
	}
	
	public BattleRng getBattleRng() {
		return battleRng;
	}

}
