package Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Stats;
import main.GameScreen;
import main.KeyInput;

public class Player extends Stats {
	
	private KeyInput key;
	
	private GameScreen gs = new GameScreen();
	
	private int experience;
	private int maxExperience;
	private int level;
	private int gold;
	
	public Player(KeyInput key) {
		
		// Input do teclado
		this.key = key;
		
		// Coordenadas iniciais do player (centro da tela)
		super.setX(337);
		super.setY(337);
		
		// Velocidade do player
		super.setWalkSpeed(3);
		
		// Direção do player
		super.setDirection("down"); 
		
	}
	
	// Atualização do estado do player
	public void update() {
		
		// Caminhada
		if (key.goingUp()) {
			super.setDirection("up");
			super.setY(super.getY() - super.getWalkSpeed());
		} else if (key.goingDown()) {
			super.setDirection("down");
			super.setY(super.getY() + super.getWalkSpeed());
		} else if (key.goingLeft()) {
			super.setDirection("left");
			super.setX(super.getX() - super.getWalkSpeed());
		} else if (key.goingRight()) {
			super.setDirection("right");
			super.setX(super.getX() + super.getWalkSpeed());
		}
		
	}

	public void draw(Graphics2D brush) {
		
		BufferedImage sprite = null;
		
		// Atualização de sprites do player
		
		//if (!key.goingUp() && !key.goingDown() && !key.goingLeft() && !key.goingRight()) {
			if (super.getDirection().equals("up")) {
				sprite = super.getIdleSprites()[0];
			} else if (super.getDirection().equals("down")) {
				sprite = super.getIdleSprites()[1];
			} else if (super.getDirection().equals("left")) {
				sprite = super.getIdleSprites()[2];
			} else if (super.getDirection().equals("right")) {
				sprite = super.getIdleSprites()[3];
			}
		//}

		// Desenha o sprite do player
		brush.drawImage(sprite, super.getX(), super.getY(), gs.getTileResolution(), gs.getTileResolution(), null);
		
	}
	
	public void attack() {
		
	}
	
	public void magic() {
		
	}
	
	public void defense() {
		
	}
	
}
