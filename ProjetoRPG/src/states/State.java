package states;

public class State {
	
	private boolean ended;
	private boolean stateChanged;
	
	public boolean isEnded() {
		return ended;
	}
	public void setEnded(boolean ended) {
		this.ended = ended;
	}
	public boolean isStateChanged() {
		return stateChanged;
	}
	public void setStateChanged(boolean stateChanged) {
		this.stateChanged = stateChanged;
	}
	
}
