package combat;

import entities.enemies.Enemie;

public class Spells {
	
	private String spell1 = "D.ENER.";
	private String spell2 = "VENENO";
	private String spell3 = "L.SANG.";
	private String spell4 = "B.FOGO";
	private String spell5 = "MALDIC.";
	
	private Effects effects;
	
	public Spells(Effects effects) {
		this.effects = effects;
	}
	
	public void castSpell(int spellId, Enemie enemie) {
		if (spellId < 0 || spellId > 4) {
			
		}
		String spell = null;
		switch(spellId) {
		case 0:
			spell = spell1;
			break;
		case 1:
			spell = spell2;
			break;
		case 2:
			spell = spell3;
			break;
		case 3:
			spell = spell4;
			break;
		case 4:
			spell = spell5;
			break;
		}
		
		if(spell.equals("D.ENER.")) {
			energyBullet(enemie);
		} else if(spell.equals("B.FOGO")) {
			fireBall(enemie);
		} else if(spell.equals("RELAMP.")) {
			lightning(enemie);
		} else if(spell.equals("L.VENT.")) {
			windBlades(enemie);
		} else if(spell.equals("L.SANG.")) {
			bloodSpear(enemie);
		} else if(spell.equals("VENENO")) {
			poison(enemie);
		} else if(spell.equals("HIPNO.")) {
			hypnosis(enemie);
		} else if(spell.equals("MALDIC.")) {
			curse(enemie);
		} else if(spell.equals("R.VIDA")) {
			healthSteal(enemie);
		} else if(spell.equals("M.SOMB.")) {
			darkMagic(enemie);
		}
		
	}
	
	
	
	
	// FEITIÇOS DE EFEITO/ATAQUE DIRETO
	public void energyBullet(Enemie enemie) {
		System.out.println("energyBullet");
	}
	public void fireBall(Enemie enemie) {
		System.out.println("fireBall");
	}
	public void lightning(Enemie enemie) {
		System.out.println("lightning");
	}
	public void windBlades(Enemie enemie) {
		System.out.println("windBlades");
	}
	public void bloodSpear(Enemie enemie) {
		System.out.println("bloodSpear");
	}
	//
	
	// FEITIÇOS DE EFEITO/ATAQUE INDIRETO
	public void poison(Enemie enemie) {
		System.out.println("poison");
	}
	public void hypnosis(Enemie enemie) {
		System.out.println("hypnosis");
	}
	public void curse(Enemie enemie) {
		System.out.println("curse");
	}
	public void healthSteal(Enemie enemie) {
		System.out.println("healthSteal");
	}
	public void darkMagic(Enemie enemie) {
		System.out.println("darkMagic");
	}
	//
	
	public String getSpell1() {
		return spell1;
	}
	public void setSpell1(String spell1) {
		this.spell1 = spell1;
	}
	
	public String getSpell2() {
		return spell2;
	}
	public void setSpell2(String spell2) {
		this.spell2 = spell2;
	}
	
	public String getSpell3() {
		return spell3;
	}
	public void setSpell3(String spell3) {
		this.spell3 = spell3;
	}
	
	public String getSpell4() {
		return spell4;
	}
	public void setSpell4(String spell4) {
		this.spell4 = spell4;
	}
	
	public String getSpell5() {
		return spell5;
	}
	public void setSpell5(String spell5) {
		this.spell5 = spell5;
	}
}
