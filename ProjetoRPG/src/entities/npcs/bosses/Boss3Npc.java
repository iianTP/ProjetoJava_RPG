package entities.npcs.bosses;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.Boss3;
import main.screen.GameScreen;

public class Boss3Npc extends BossNpc {
	
	public Boss3Npc(GameScreen gs) {
		super(gs);
		super.setEnemie(new Boss3(gs));
		super.setLocation("castle3");
		this.setSprites();
	}
	
	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/bosses/boss3.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
