package states;

import main.KeyInput;

public class Dialogue {
	
	private KeyInput key;
	private int dialogueIndex = 0;
	private boolean dialogueEnded = false;
	private boolean dialogueChanged;
	
	private String[] dialogue;
	
	public Dialogue(KeyInput key, String[] dialogue) {
		this.key = key;
		this.dialogue = dialogue;
	}
	
	public void dialogue() {
		this.dialogueChanged = false;
		
		if (this.key.isInteracting()) {
			this.dialogueChanged = true;
			this.dialogueIndex++;
			if (this.dialogueIndex == this.dialogue.length) {
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

	public boolean isDialogueChanged() {
		return dialogueChanged;
	}

	public String getDialogue() {
		return dialogue[this.dialogueIndex];
	}

}
