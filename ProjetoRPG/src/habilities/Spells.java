package habilities;

public class Spells {
	
	private String spell1 = "E.BULL.";
	private String spell2 = "POISON";
	private String spell3 = "B.SPEAR";
	private String spell4 = "F.BALL";
	private String spell5 = "CURSE";
	
	
	public void castSpell(String spellId) {
		
		if(spellId.equals("E.BULL.")) {
			energyBullet();
		} else if(spellId.equals("F.BALL")) {
			fireBall();
		} else if(spellId.equals("LIGHT.")) {
			lightning();
		} else if(spellId.equals("W.BLADE")) {
			windBlades();
		} else if(spellId.equals("B.SPEAR")) {
			bloodSpear();
		} else if(spellId.equals("POISON")) {
			poison();
		} else if(spellId.equals("HYPNO.")) {
			hypnosis();
		} else if(spellId.equals("CURSE")) {
			curse();
		} else if(spellId.equals("H.STEAL")) {
			healthSteal();
		} else if(spellId.equals("D.MAGIC")) {
			darkMagic();
		}
		
	}
	
	
	
	
	// FEITIÇOS DE EFEITO/ATAQUE DIRETO
	public void energyBullet() {
		System.out.println("energyBullet");
	}
	public void fireBall() {
		System.out.println("fireBall");
	}
	public void lightning() {
		System.out.println("lightning");
	}
	public void windBlades() {
		System.out.println("windBlades");
	}
	public void bloodSpear() {
		System.out.println("bloodSpear");
	}
	//
	
	// FEITIÇOS DE EFEITO/ATAQUE INDIRETO
	public void poison() {
		System.out.println("poison");
	}
	public void hypnosis() {
		System.out.println("hypnosis");
	}
	public void curse() {
		System.out.println("curse");
	}
	public void healthSteal() {
		System.out.println("healthSteal");
	}
	public void darkMagic() {
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
