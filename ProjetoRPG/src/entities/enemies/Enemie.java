package entities.enemies;

import java.awt.image.BufferedImage;

import combat.BattleRng;
import entities.Battler;
import entities.Stats;
import entities.teammates.Team;
import exceptions.InvalidStatsInputException;
import exceptions.InvalidTargetException;
import main.screen.GameScreen;
import states.Battle;

public abstract class Enemie extends Battler {
	
	private BufferedImage sprite;
	private final BattleRng battleRng = new BattleRng(super.getRng());
	
	private int experience;
	private int gold;
	
	public Enemie(GameScreen gs) {
		super(gs);
	}
	
	public BattleRng getBattleRng() {
		return battleRng;
	}
	
	// MÉTODOS DE COMBATE
	public void battleMove(Team target, Battle battle) {
		
		String move = this.battleRng.chooseMove(super.getStats().getOverdrive());
		
		if (move.equals("attack")) {
			
			try {
				this.attack(target, battle);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
			
		} else if (move.equals("defense")) {
			super.defend(battle);
		} else if (move.equals("magic")) {
			
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
	
	@Override
	public <T extends Battler> void attack(T target, Battle battle) throws InvalidTargetException {
		if (target instanceof Team) {
			super.attack(target, battle);
		} else {
			throw new InvalidTargetException("alvo não é do tipo Team");
		}
	}

	@Override
	public <T extends Battler> void magic(T target, int spellId, Battle battle) throws InvalidTargetException {
		if (target instanceof Team) {
			super.magic(target, spellId, battle);
		} else {
			throw new InvalidTargetException("alvo não é do tipo Team");
		}
	}
	
	@Override
	public <T> void special(T target, Battle battle) {
		try {
			if (target instanceof Team) {
				super.getStats().alterMana(-10);
				super.getStats().heal(5);
				battle.setMessage(super.getName()+" USOU SEU ESPECIAL (-10MANA +5HP)");
			} else {
					throw new InvalidTargetException("alvo não é do tipo Team");
			}
		} catch (InvalidTargetException e) {
			e.printStackTrace();
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
	}
	
	//
	
	
	
	public Stats getStats() {
		return super.getStats();
	}
	public BufferedImage getSprite() {
		return this.sprite;
	}
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}


}
