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
	private BattleRng battleRng = new BattleRng();
	
	public Enemie(GameScreen gs) {
		super(gs);
	}
	
	// MÉTODOS DE COMBATE
	
	public void battleMove(Team target) {
		int randomNumber = this.battleRng.rng(this.battleRng.getFullChance(), 1);
		
		if (randomNumber <= this.battleRng.getAttackChance()) {
			try {
				this.attack(target, null);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
		} else {
			this.defend(null);
		}
	}
	
	public void battleMove(Team target, Battle battle) {
		
		String move = this.battleRng.chooseMove();
		
		if (move.equals("attack")) {
			
			try {
				this.attack(target, battle);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
			
			battle.setMessage("O "+this.getName()+" ATACOU");
			
		} else if (move.equals("defense")) {
			
			this.defend(battle);
			
			battle.setMessage("O "+this.getName()+" DESCANCOU");
			
		} else {
			
			int spellId = this.battleRng.getRandomSpellId(super.getSpells(), super.getStats().getMana());
			if (spellId != -1) {
				try {
					battle.setMessage("O "+this.getName()+" USOU "+super.getSpells().getSpell(spellId).getSpellName().toUpperCase());
					this.magic(target, spellId, battle);
				} catch (InvalidTargetException e) {
					e.printStackTrace();
				}
			} else {
				battle.setMessage("O "+this.getName()+" TENTOU USAR UM FEITICO, MAS FALHOU");
			}
		}
	}
	
	@Override
	public <T> void attack(T target, Battle battle) throws InvalidTargetException {
		if (target instanceof Team) {
			((Team) target).takeDamage(super.getStats().getStrength());
			this.battleRng.increaseAttackChance();
		} else {
			throw new InvalidTargetException("alvo não é do tipo Team");
		}
	}

	@Override
	public <T> void magic(T target, int spellId, Battle battle) throws InvalidTargetException {
		if (target instanceof Team) {
			super.getSpells().getSpell(spellId).castSpell((Team)target, super.getStats());
		} else {
			throw new InvalidTargetException("alvo não é do tipo Team");
		}
	}
	
	@Override
	public void defend(Battle battle) {
		if (super.getStats().getHealth() < super.getStats().getMaxHealth()) {
			try {
				
				super.getStats().heal(this.battleRng.rng(super.getStats().getDefense()/2, 0));
				
				if (super.getStats().getHealth() > super.getStats().getMaxHealth()) {
					super.getStats().setHealth(super.getStats().getMaxHealth());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
		}
		if (super.getStats().getMana() < super.getStats().getMaxMana()) {
			try {
				super.getStats().alterMana(this.battleRng.rng(super.getStats().getMagicDefense(), 0));
				if (super.getStats().getMana() > super.getStats().getMaxMana()) {
					super.getStats().setMana(super.getStats().getMaxMana());
				}
			} catch (InvalidStatsInputException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public <T> void special(T target, Battle battle) {
		
	}
	
	@Override
	public void takeDamage(int damage) {
		try {
			super.getStats().damage(damage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.battleRng.increaseDefenseChance();
	}
	
	@Override
	public void takeMagicDamage(int magicDamage) {
		int magicDefense = super.getStats().getMagicDefense();
		int finalDamage = 2*magicDamage/magicDefense;
		try {
			super.getStats().damage(finalDamage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.battleRng.increaseDefenseChance();
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


}
