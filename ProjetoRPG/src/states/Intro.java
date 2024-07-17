package states;

import main.KeyInput;

public class Intro {
	
	private String[] texts = {
			"NO INICIO, HAVIAM 4 MUNDOS  QUE VIVIAM EM HARMONIA",
			"UM DIA, OS GOVERNANTES DE   CADA MUNDO ENTRARAM EM      CONFLITO ENTRE SI", 
			"FOI INICIADA ASSIM UMA      GRANDE GUERRA",
			"APOS ANOS DE BATALHAS, OS   LIDERES ENTRARAM EM UM      ACORDO",
			"O ACORDO CONSISTIA NO       ISOLAMENTO TOTAL DE CADA MUNDO PARA QUE ELES NUNCA MAIS ENTRASSEM EM CONTATO",
			"ESTE ISOLAMENTO PERSISTE ATEENTAO",
			"...",
			"...?",
			"?#$x* aANos   ddEpOIs...",
			"FRAGMENTOS MISTERIOSOS      SURGIRAM EM CADA UM DOS 3   MUNDOS",
			"ESTE EVENTO DETERMINOU ENTAOO DIA DA GRANDE REUNIAO",
			"OS MUNDOS ENTAO ESTAVAM EM  HARMONIA NOVAMENTE",
			".      .      ."
	};
	
	private int textIndex = 0;
	
	private KeyInput key;
	
	private boolean changedState;
	
	private boolean introEnded = false;

	public Intro(KeyInput key) {
		this.key = key;
	}
	
	public void intro() {
		
		this.changedState = false;
		
		if (this.key.isInteracting()) {
			
			this.changedState = true;
			this.textIndex++;
			if (this.textIndex >= this.texts.length) {
				this.introEnded = true;
			}
		}
		
	}
	
	public String getCurrentText() {
		return this.texts[this.textIndex];
	}
	
	public boolean isFirstText() {
		return this.textIndex == 0;
	}
	
	public void changeFirstText() {
		this.texts[0] = "NO INICIO, HAVIAM 3 MUNDOS  QUE VIVIAM EM HARMONIA";
	}

	public boolean isChangedState() {
		return changedState;
	}

	public boolean isIntroEnded() {
		return introEnded;
	}
}
