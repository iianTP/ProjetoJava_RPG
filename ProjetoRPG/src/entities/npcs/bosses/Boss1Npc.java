package entities.npcs.bosses;

import java.io.IOException;

import javax.imageio.ImageIO;

import entities.enemies.Boss1;
import main.screen.GameScreen;

public class Boss1Npc extends BossNpc {

	public Boss1Npc(GameScreen gs) {
		super(gs);
		super.setEnemie(new Boss1(gs));
		super.setLocation("castle1");
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
