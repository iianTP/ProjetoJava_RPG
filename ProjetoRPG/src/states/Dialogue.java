package states;

import main.KeyInput;

public class Dialogue {
	
	private KeyInput key;
	private int dialogueLength;
	private int dialogueIndex = 0;
	private boolean dialogueEnded = false;
	
	public Dialogue(KeyInput key, int dialogueLength) {
		this.key = key;
		this.dialogueLength = dialogueLength;
	}
	
	public void dialogue() {
		if (this.key.isInteracting()) {
			dialogueIndex++;
			if (this.dialogueIndex == this.dialogueLength) {
				this.dialogueEnded = true;
			}
		}
	}
	
	public int getDialogueIndex() {
		return this.dialogueIndex;
	}

	public boolean isDialogueEnded() {
		return dialogueEnded;
	}

}
