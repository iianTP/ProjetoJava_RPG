package combat;

import entities.Stats;
import exceptions.InvalidStatsInputException;

public class Effects {
	
	private String currentEffect = "burning";
	private Stats stats;
	
	private String message = "teste";
	
	public Effects(Stats stats) {
		this.stats = stats;
	}
	
	public void effect() {
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
			// exception
		}
	}
	
	private void burning() {
		
	}
	private void paralyzed() {
			
	}
	private void poisoned() {
		
	}
	private void cursed() {
		
	}
	private void hypnotized() {
		
	}
	private void bleeding() {
		try {
			this.stats.damage(1);
		} catch (InvalidStatsInputException e) {
			e.printStackTrace();
		}
	}
	
	public String getCurrentEffect() {
		return this.currentEffect;
	}
	public void setCurrentEffect(String currentEffect) {
		this.currentEffect = currentEffect;
	}

	public String getMessage() {
		return message;
	}
}
