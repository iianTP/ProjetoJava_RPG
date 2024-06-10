package entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entities.npcs.Npc;
import main.KeyInput;
import main.screen.GameScreen;

public abstract class Player extends Entity {
	
	private KeyInput key;
	private Npc[] npcs;
	private Collision collision = new Collision();
	private Stats stats;
	
	private int screenX = super.getGs().getScreenSide()/2 - super.getGs().getTileSide()/2;
	private int screenY = super.getGs().getScreenSide()/2 - super.getGs().getTileSide()/2;

	private int experience = 0;
	private int maxExperience = 20;
	private int level = 1;
	private int gold = 0;
	
	private int[][] hitbox = {{12, 30}, {33, 45}};
	
	private Color black100Faded = new Color(0,0,0,100);

	public Player(KeyInput key, Npc[] npcs, GameScreen gs) {
		super(gs);
		this.key = key; // Input do teclado
		this.npcs = npcs;
		
		// Coordenadas iniciais do player (centro da tela)
		super.setX(2160/2);
		super.setY(2160/2);
		
		super.setWalkSpeed(3); // Velocidade do player
		super.setDirection("down"); // Direção do player
		
	}
	
	public abstract void setStats();
	
	// Atualização do estado do player
	public void update() {
		
		walk();
		
		if (this.key.isInteracting()) {
			interact();
		}
		
		while (this.experience >= this.maxExperience) {
			levelUp();
		}
		
		super.addSpriteCount();
		
		if (super.getSpriteCount() >= 20) {
			super.switchAnimationFrame();
			super.resetSpriteCount();
		}
	}
	//
	
	public void draw(Graphics2D brush, int gameState) {
		
		BufferedImage sprite = null;
		
		// Atualização de sprites do player
		if (this.key.notWalking() || gameState == 2) {
			
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
		//
		
		// SOMBRA
		brush.setColor(this.black100Faded);
		brush.drawRect(this.screenX, this.screenY-1, 48, 48);
		brush.fillOval(this.screenX, this.screenY+40, 48, 15);
		//

		// Desenha o sprite do player
		brush.drawImage(sprite, this.screenX, this.screenY, super.getGs().getTileSide(), super.getGs().getTileSide(), null);
		//
	}
	
	// Caminhada
	public void walk() {
		
		if (!this.key.notWalking()) {
			
			if (this.key.goingUp()) {
				super.setDirection("up");
			} else if (this.key.goingDown()) {
				super.setDirection("down");
			} else if (this.key.goingLeft()) {
				super.setDirection("left");
			} else if (this.key.goingRight()) {
				super.setDirection("right");
			}
			
			super.setCollision(false);
			
			super.setX(super.getX() - 24);
			super.setY(super.getY() - 24);
			
			this.collision.checkTile(this);
			this.collision.checkNpc(this, npcs);
			
			super.setX(super.getX() + 24);
			super.setY(super.getY() + 24);
			
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
	
	public void interact() {
		
		super.setCollision(false);
		
		super.setX(super.getX() - 24);
		super.setY(super.getY() - 24);
		
		this.collision.checkNpc(this, npcs);
		
		super.setX(super.getX() + 24);
		super.setY(super.getY() + 24);
		
		if (super.getCollision()) {
			this.collision.getNpcNearby().interaction();
		}
		
	}
	
	public boolean checkHitbox(int npcX, int npcY) {

		int x = super.getX()-24;
		int y = super.getY()-24;
		
		if (npcX > hitbox[0][0] + x && npcX < hitbox[1][0] + x && 
			npcY > hitbox[0][1] + y && npcY < hitbox[1][1] + y) {
			return true;
		} else {
			return false;
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
		
		//super.buffStats();
		
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
	
	@Override
	public int[][] getHitbox() {
		return hitbox;
	}

	public void setHitbox(int[][] hitbox) {
		this.hitbox = hitbox;
	}
	
	public int getScreenY() {
		return this.screenY;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	
}
