package states;

import main.KeyInput;

public class Intro extends State {
	
	private String[] texts = {
			"NO INICIO, HAVIAM 4 MUNDOS  QUE VIVIAM EM HARMONIA",
			"UM DIA, OS GOVERNANTES DE   CADA MUNDO ENTRARAM EM      CONFLITO ENTRE SI", 
			"FOI INICIADA ASSIM UMA      GRANDE GUERRA",
			"APOS ANOS DE BATALHAS, OS   LIDERES ENTRARAM EM UM      ACORDO",
			"O ACORDO CONSISTIA NO       ISOLAMENTO TOTAL DE CADA    MUNDO PARA QUE ELES NUNCA   MAIS ENTRASSEM EM CONTATO",
			"ESTE ISOLAMENTO PERSISTE ATEENTAO",
			"...",
			".....",
			".......?",
			"?#$x* aANos   ddEpOIs...",
			"FRAGMENTOS MISTERIOSOS      SURGIRAM EM CADA UM DOS 3   MUNDOS",
			"ESTE EVENTO DETERMINOU ENTAOO DIA DA GRANDE REUNIAO",
			"OS MUNDOS ENTAO ESTAVAM EM  HARMONIA NOVAMENTE",
			".      .      .      .",
			"VOZ 1: ???",
			"VOZ 2: O QUE!?, NAO ACREDITO!",
			"VOZ 3: O QUARTO \"JOGADOR\"   FINALMENTE APARECEU!",
			"VOZ 1: SERA QUE FINALMENTE  VAMOS CONSEGUIR SAIR DAQUI?",
			"VOZ 3: SILENCIO, ELE TA     ACORDANDO",
			"...",
			"VOZ 1: ESCUTE BEM, NOS      PROVAVELMENTE NAO VAMOS     CONVERSAR DE NOVO DAQUI PRA FRENTE...",
			"VOZ 3: ...ESTAMOS AQUI A    TANTO TEMPO...",
			"VOZ 1: APENAS PROCURE UMA   FORMA DE TIRAR A GENTE DESSELUGAR"
	};
	
	private int textIndex = 0;
	
	private KeyInput key;
	
	public Intro(KeyInput key) {
		this.key = key;
	}
	
	public void intro() {
		
		super.setStateChanged(false);
		if (this.key.isInteracting()) {
			super.setStateChanged(true);
			this.textIndex++;
			if (this.textIndex >= this.texts.length) {
				super.setEnded(true);
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

}
