package entities.npcs;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.player.Player;
import main.screen.GameScreen;

public class SentinelNpc extends Npc {

	public SentinelNpc(GameScreen gs, int x, int y) {
		super(gs);
		super.setX(x);
		super.setY(y);
		super.setLocation("world1");
		super.setDialogue(new String[] {". . ."});
		this.setSprites();
	}

	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/npcs/sentinelNpc.png")));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void interaction(Player player) {
		super.getGs().setDialogueState();
	}

}
