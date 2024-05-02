package Player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Entities.Collision;
import Entities.Stats;
import Tiles.TileManager;
import main.GameScreen;
import main.KeyInput;
import main.ScreenInfo;

public class Player extends Stats {
	
	private KeyInput key;
	private ScreenInfo screen;
	private Collision collision;

	private int experience;
	private int maxExperience;
	private int level;
	private int gold;
	
	public Player(KeyInput key) {
		
		this.key = key; // Input do teclado
		this.screen = new ScreenInfo();
		this.collision = new Collision();
		
		// Coordenadas iniciais do player (centro da tela)
		super.setX(336);
		super.setY(336);
		
		super.setWalkSpeed(3); // Velocidade do player
		super.setDirection("down"); // Direção do player
		
		this.experience = 0;
		this.maxExperience = 20;
		this.level = 1;
		this.gold = 0;
		
	}
	
	// Atualização do estado do player
	public void update() {
		
		walk();
		
		while(this.experience >= this.maxExperience) {
			levelUp();
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
		brush.drawImage(sprite, super.getX(), super.getY(), this.screen.tileSide(), this.screen.tileSide(), null);
		
	}
	
	// Caminhada
	public void walk() {
		
		if (!key.notWalking()) {
			
			if (key.goingUp()) {
				super.setDirection("up");
			} else if (key.goingDown()) {
				super.setDirection("down");
			} else if (key.goingLeft()) {
				super.setDirection("left");
			} else if (key.goingRight()) {
				super.setDirection("right");
			}
			
			super.setCollision(false);
			this.collision.collision(this);
			
			if (!super.getCollision()) {
				
				if (super.getDirection().equals("up")) {
					super.setY(super.getY() - super.getWalkSpeed());
				} else if (super.getDirection().equals("down")) {
					super.setY(super.getY() + super.getWalkSpeed());
				} else if (super.getDirection().equals("left")) {
					super.setX(super.getX() - super.getWalkSpeed());
				} else if (super.getDirection().equals("right")) {
					super.setX(super.getX() + super.getWalkSpeed());
				}
				
			}
			
		}
			
		
	}
	
	

	public void addExperience(int experience) {
		this.experience += experience;
	}
	
	public void addGold(int gold) {
		this.gold += gold;
	}
	
	public void reduceGold(int gold) {
		this.gold -= gold;
	}
	
	public void levelUp() {
		
		this.level += 1;
		this.experience = 0;
		this.maxExperience += 5;
		
		super.buffStats();
		
	}
	
	public int getExperience() {
		return this.experience;
	}
	
	public int getMaxExperience() {
		return this.maxExperience;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getGold() {
		return this.gold;
	}
	
	
}
