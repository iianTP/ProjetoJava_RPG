package entities;

import combat.Effects;
import combat.spells.KnownSpells;
import interfaces.ICombat;
import main.screen.GameScreen;

public abstract class Battler extends Entity implements ICombat {

	private Stats stats;
	private Effects effects;
	private KnownSpells spells;
	
	private String name;
	
	public Battler(GameScreen gs) {
		super(gs);
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
