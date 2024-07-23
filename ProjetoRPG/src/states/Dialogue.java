package states;

import main.KeyInput;

public class Dialogue extends State {
	
	private KeyInput key;
	private int dialogueIndex = 0;
	
	private String[] dialogue;
	
	public Dialogue(KeyInput key, String[] dialogue) {
		this.key = key;
		this.dialogue = dialogue;
	}
	
	public void dialogue() {
		
		super.setStateChanged(false);
		
		if (this.key.isInteracting()) {
			super.setStateChanged(true);
			this.dialogueIndex++;
			if (this.dialogueIndex == this.dialogue.length) {
				super.setEnded(true);
			}
		}
	}
	
	public String getDialogue() {
		return dialogue[this.dialogueIndex];
	}

}
