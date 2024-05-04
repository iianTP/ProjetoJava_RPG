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
	
	private int screenX;
	private int screenY;

	private int experience;
	private int maxExperience;
	private int level;
	private int gold;
	
	public Player(KeyInput key) {
		
		this.key = key; // Input do teclado
		this.screen = new ScreenInfo();
		this.collision = new Collision();
		
		// Coordenadas iniciais do player (centro da tela)
		super.setX(2160/2);
		super.setY(2160/2);
		
		super.setWalkSpeed(3); // Velocidade do player
		super.setDirection("down"); // Direção do player
		
		this.screenX = this.screen.screenSide()/2 - this.screen.tileSide()/2;
		this.screenY = this.screen.screenSide()/2 - this.screen.tileSide()/2;
		
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
		
		super.addSpriteCount();
		
		if (super.getSpriteCount() >= 20) {
			super.switchAnimationFrame();
			super.resetSpriteCount();
		}
	}

	public void draw(Graphics2D brush) {
		
		BufferedImage sprite = null;
		
		// Atualização de sprites do player
		
		if (!key.goingUp() && !key.goingDown() && !key.goingLeft() && !key.goingRight()) {
			if (super.getDirection().equals("up")) {
				
				sprite = super.getIdleSprites()[0];
				
			} else if (super.getDirection().equals("down")) {
				
				sprite = super.getIdleSprites()[1];
				
			} else if (super.getDirection().equals("left")) {
				
				sprite = super.getIdleSprites()[2];
				
			} else if (super.getDirection().equals("right")) {
				
				sprite = super.getIdleSprites()[3];
				
			}
		} else {
			
			if (super.getDirection().equals("up")) {
				
				if (super.getAnimationFrame() == 1) {
					sprite = super.getWalkSprites()[0][0];
				} else {
					sprite = super.getWalkSprites()[1][0];
				}
				
				
			} else if (super.getDirection().equals("down")) {
				
				if (super.getAnimationFrame() == 1) {
					sprite = super.getWalkSprites()[0][1];
				} else {
					sprite = super.getWalkSprites()[1][1];
				}
				
			} else if (super.getDirection().equals("left")) {
				
				if (super.getAnimationFrame() == 1) {
					sprite = super.getWalkSprites()[0][2];
				} else {
					sprite = super.getWalkSprites()[1][2];
				}
				
			} else if (super.getDirection().equals("right")) {
				
				if (super.getAnimationFrame() == 1) {
					sprite = super.getWalkSprites()[0][3];
				} else {
					sprite = super.getWalkSprites()[1][3];
				}
				
			}
			
		}

		// Desenha o sprite do player
		brush.drawImage(sprite, this.screenX, this.screenY, this.screen.tileSide(), this.screen.tileSide(), null);
		
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
					super.goUp();
				} else if (super.getDirection().equals("down")) {
					super.goDown();
				} else if (super.getDirection().equals("left")) {
					super.goLeft();
				} else if (super.getDirection().equals("right")) {
					super.goRight();
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
