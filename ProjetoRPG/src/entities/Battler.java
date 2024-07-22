package entities;

import java.util.Random;

import combat.Effects;
import combat.spells.KnownSpells;
import combat.spells.Spell;
import entities.enemies.Enemie;
import entities.teammates.Teammate;
import exceptions.InvalidSpellIdException;
import exceptions.InvalidStatsInputException;
import exceptions.InvalidTargetException;
import interfaces.ICombat;
import main.screen.GameScreen;
import states.Battle;

public abstract class Battler extends Entity implements ICombat {

	private Stats stats;
	private Effects effects;
	private KnownSpells spells;
	
	private String name;
	
	public Battler(GameScreen gs) {
		super(gs);
	}
	
	@Override
	public <T extends Battler> void attack(T target, Battle battle) throws InvalidTargetException {
		if (!this.effects.getCurrentEffect().equals("paralyzed")) {
			Stats stats = this.getStats();
			
			int criticalChance = stats.getCriticalDamage();
			int strength = stats.getStrength();
			int targetDefense = target.getStats().getDefense();
			int targetAgility = target.getStats().getAgility();
			int critical = (super.rng(100, 1) <= criticalChance) ? 2 : 1;
			int finalDamage = critical*2*strength/targetDefense;
			
			int dodge = super.rng(20, 1);
			
			if (dodge <= targetAgility && dodge > 0) {
				battle.setMessage(this.name+" TENTOU ATACAR "+target.getName()+" MAS ERROU");
			} else {
				
				target.takeDamage(finalDamage);
				stats.increaseOverdrive();
				battle.setMessage(this.name+" ATACOU "+target.getName()+" (-"+finalDamage+"HP)");
				if (this instanceof Teammate) {
					((Teammate) this).getBattleRng().increaseAttackChance();
				} else if (this instanceof Enemie) {
					((Enemie) this).getBattleRng().increaseAttackChance();
				}
				
			}
		}
	}
	
	@Override
	public <T extends Battler> void magic(T target, int spellId, Battle battle) throws InvalidTargetException {
		if (!this.effects.getCurrentEffect().equals("paralyzed")) {
			try {
				Spell spell = this.getSpells().getSpell(spellId+1);
				String msg = this.name+" USOU "
						+spell.getSpellName().toUpperCase()
						+" EM "+target.getName();
				
				int dodge = new Random().nextInt(20)+1;
				
				if (dodge <= target.getStats().getAgility() && dodge > 0) {
					battle.setMessage(msg+" MAS ERROU");
					this.getStats().alterMana(spell.getManaCost());
				} else {
					int hpBefore = target.getStats().getHealth();
					
					this.getSpells().castSpell(spellId, target, battle);
					this.getStats().increaseOverdrive();
					
					int difference = hpBefore-target.getStats().getHealth();
					battle.setMessage(msg+" (-"+difference+"HP)");
					
					if (this instanceof Teammate) {
						((Teammate) this).getBattleRng().increaseAttackChance();
					} else if (this instanceof Enemie) {
						((Enemie) this).getBattleRng().increaseAttackChance();
					}
				}
				
			} catch (InvalidSpellIdException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void defend(Battle battle) {
		
		Stats stats = this.getStats();
		
		int heal = 0;
		int mana = 0;
		try {
			if (stats.getHealth() < stats.getMaxHealth()) {
				heal = super.rng(stats.getDefense(), 0);
				stats.heal(heal);
				if (stats.getHealth() > stats.getMaxHealth()) {
					stats.setHealth(stats.getMaxHealth());
				}
			}
			if (stats.getMana() < stats.getMaxMana()) {
				mana = super.rng(2*stats.getMagicDefense(), 0);
				stats.alterMana(mana);
				if (stats.getMana() > stats.getMaxMana()) {
					stats.setMana(stats.getMaxMana());
				}
			}
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		
		battle.setMessage(this.name+" DESCANCOU (+"+heal+"HP +"+mana+" MANA)");
		
	}
	
	@Override
	public void takeDamage(int damage) {
		Stats stats = this.getStats();
		try {
			stats.damage(damage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void takeMagicDamage(int magicDamage) {
		Stats stats = this.getStats();
		try {
			stats.damage(magicDamage);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
	}
	
	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
		this.effects = new Effects(this.stats);
		this.spells = new KnownSpells(this.stats);
	}

	public Effects getEffects() {
		return effects;
	}

	public KnownSpells getSpells() {
		return spells;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
