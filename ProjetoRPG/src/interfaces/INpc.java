package interfaces;

import entities.player.Player;

public interface INpc {

	public void setSprites();
	
	public void action();
	
	public void interaction(Player player);
	
}
