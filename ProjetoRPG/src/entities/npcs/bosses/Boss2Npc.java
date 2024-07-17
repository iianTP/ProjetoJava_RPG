package entities.npcs.bosses;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.Boss2;
import main.screen.GameScreen;

public class Boss2Npc extends BossNpc {

	public Boss2Npc(GameScreen gs) {
		super(gs);
		super.setEnemie(new Boss2(gs));
		super.setLocation("castle2");
		this.setSprites();
	}
	
	@Override
	public void setSprites() {
		try {
			super.setSprite(ImageIO.read(getClass().getResourceAsStream("/assassin/AssassinIdleDown.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
