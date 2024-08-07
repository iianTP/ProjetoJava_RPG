package states.battle;

import entities.Stats;
import exceptions.InvalidEffectException;
import exceptions.InvalidStatsInputException;

public class Effects {
	
	private String currentEffect = "none";
	private Stats stats;
	
	private String message;
	
	private int defenseBackup;
	private int agilityBackup;
	
	private int effectCounter = 0;
	
	private int bleedingDamage = 1;
	
	public Effects(Stats stats) {
		this.stats = stats;
	}
	
	public void effect() {
		
		this.defenseBackup = this.stats.getDefense();
		this.agilityBackup = this.stats.getAgility();
		
		if (this.currentEffect.equals("burning")) {
			burning();
		} else if (this.currentEffect.equals("paralyzed")) {
			paralyzed();
		} else if (this.currentEffect.equals("poisoned")) {
			poisoned();
		} else if (this.currentEffect.equals("cursed")) {
			cursed();
		} else if (this.currentEffect.equals("hypnotized")) {
			hypnotized();
		} else if (this.currentEffect.equals("bleeding")) {
			bleeding();
		} else if (!this.currentEffect.equals("none")) {
			try {
				throw new InvalidEffectException(this.currentEffect);
			} catch (InvalidEffectException e) {
				e.printStackTrace();
			}
		}
		
		if (!this.currentEffect.equals("none")) {
			this.effectCounter++;
			if (effectCounter > 3) {
				this.effectCounter = 0;
				this.stopEffect();
			}
		}
	}
	
	private void stopEffect() {
		try {
			this.stats.setDefense(this.defenseBackup);
			this.stats.setAgility(this.agilityBackup);
			this.bleedingDamage = 1;
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.currentEffect = "none";
	}
	
	private void burning() {
		try {
			this.stats.damage(3);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.message = "ESTA QUEIMANDO (-3HP)";
	}
	private void paralyzed() {
		this.message = "ESTA PARALIZADO";
	}
	private void poisoned() {
		try {
			this.stats.damage(5);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.message = "ESTA ENVENENADO (-3HP)";
	}
	private void cursed() {
		try {
			this.stats.setDefense(1);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.message = "ESTA AMALDICOADO";
	}
	private void hypnotized() {
		try {
			this.stats.setAgility(0);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.message = "ESTA HIPNOTIZADO";
	}
	private void bleeding() {
		try {
			this.stats.damage(bleedingDamage);
			this.bleedingDamage += 5;
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
		this.message = "ESTA SANGRANDO (-"+this.bleedingDamage+"HP)";
	}
	
	public String getCurrentEffect() {
		return this.currentEffect;
	}
	public void setCurrentEffect(String currentEffect) {
		
		if (!this.currentEffect.equals("none")) {
			return;
		}
		this.currentEffect = currentEffect;
		
	}

	public String getMessage() {
		return message;
	}
}
