package entities.teammates;

import combat.BattleRng;
import entities.Stats;
import entities.enemies.Enemie;
import exceptions.InvalidStatsInputException;
import exceptions.InvalidTargetException;
import interfaces.ICombat;
import main.screen.GameScreen;
import states.Battle;

public abstract class Teammate extends Team implements ICombat {
	
	private BattleRng battleRng = new BattleRng();
	private String name;
	
	public Teammate(GameScreen gs) {
		super(gs);
	}
	
	public void battleMove(Enemie enemie, Battle battle) {
		
		String move = this.battleRng.chooseMove();
		
		if (move.equals("attack")) {
			
			int enemieHpBefore = enemie.getStats().getHealth();
			try {
				this.attack(enemie, null);
			} catch (InvalidTargetException e) {
				e.printStackTrace();
			}
			int enemieHpDifference = enemie.getStats().getHealth() - enemieHpBefore;
			
			battle.setMessage("O "+this.name+" GOLPEOU O INIMIGO (-"+enemieHpDifference+"HP)");
			
		} else if (move.equals("defense")) {
			
			int teammateHpBefore = super.getStats().getHealth();
			
			this.defend(null);
			int teammateHpDifference = super.getStats().getHealth() - teammateHpBefore;
			
			battle.setMessage("O "+this.name+" DESCANCOU (+"+teammateHpDifference+"HP)");
			
		} else {
			
			int spellId = this.battleRng.getRandomSpellId(super.getSpells(), super.getStats().getMana());
			if (spellId != -1) {
				try {
					battle.setMessage("O "+this.name+" USOU "+super.getSpells().getSpell(spellId).getSpellName().toUpperCase());
					this.magic(enemie, spellId, null);
				} catch (InvalidTargetException e) {
					e.printStackTrace();
				}
			} else {
				battle.setMessage("O "+this.name+" TENTOU USAR UM FEITICO, MAS FALHOU");
			}
		}
	}
	
	@Override
	public <T> void attack(T target, Battle battle) throws InvalidTargetException {
		Stats stats = super.getStats();
		if (target instanceof Enemie) {
			((Enemie) target).takeDamage(stats.getStrength());
			this.battleRng.increaseAttackChance();
		} else {
			throw new InvalidTargetException("alvo não é do tipo Enemie");
		}
	}

	@Override
	public <T> void magic(T target, int spellId, Battle battle) throws InvalidTargetException {
		if (target instanceof Enemie) {
			super.getSpells().getSpell(spellId).castSpell((Enemie)target, super.getStats());
		} else {
			throw new InvalidTargetException("alvo não é do tipo Enemie");
		}
	}
	
	@Override
	public void defend(Battle battle) {
		Stats stats = super.getStats();		
		if (stats.getHealth() < stats.getMaxHealth()) {
			try {
				stats.heal(this.battleRng.rng(stats.getDefense(), 0));
				if (stats.getHealth() > stats.getMaxHealth()) {
					stats.setHealth(stats.getMaxHealth());
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
	public void takeDamage(int damage) {
		Stats stats = super.getStats();
		try {
			stats.damage(damage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.battleRng.increaseDefenseChance();
	}
	
	@Override
	public void takeMagicDamage(int magicDamage) {
		Stats stats = super.getStats();
		try {
			stats.damage(magicDamage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}

}
