package entities.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import combat.Effects;
import combat.Spells;
import items.*;

import entities.Collision;
import entities.Entity;
import entities.Stats;
import entities.enemies.Enemie;
import entities.npcs.Npc;
import entities.npcs.teammates.Teammate;
import exceptions.InventoryIsFullException;
import interfaces.ICombat;
import main.KeyInput;
import main.screen.GameScreen;

public abstract class Player extends Entity implements ICombat {
	
	private final KeyInput key;
	private Npc[] npcs;
	private Teammate[] teammates;
	private final Collision collision = new Collision();
	private Stats stats;
	private Inventory inventory;
	
	private int screenX = super.getGs().getScreenSide()/2 - super.getGs().getTileSide()/2;
	private int screenY = super.getGs().getScreenSide()/2 - super.getGs().getTileSide()/2;

	private int experience = 0;
	private int maxExperience = 20;
	private int level = 1;
	private int gold = 999;
	
	private int[][] hitbox = {{12, 30}, {33, 45}};
	
	private final Color black100Faded = new Color(0,0,0,100);
	
	private Effects effects = new Effects(this.stats);
	private Spells spells = new Spells(this.effects);
	
	private Item armorEquiped;
	private Item weaponEquiped;
	
	public Player(KeyInput key, GameScreen gs) {
		super(gs);
		this.key = key; // Input do teclado
		this.inventory = new Inventory(this.key);
		
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
	
	public void draw(Graphics2D brush) {
		
		BufferedImage sprite = null;
		
		// Atualização de sprites do player
		if (this.key.notWalking() || super.getGs().getGameState() == 2) {
			
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
			if (npcs != null) {
				this.collision.checkNpc(this, npcs);
			}
			
			super.setX(super.getX() + 24);
			super.setY(super.getY() + 24);
			
			if (!super.isColliding()) {
				
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
		
		if (super.isColliding()) {
			this.collision.getNpcNearby().interaction();
			this.collision.getNpcNearby().interaction(this);
		}
		
	}
	
	
	
	
	// MÉTODOS DE COMBATE
	
	@Override
	public <T> void attack(T target) {
		if (target instanceof Enemie) {
			((Enemie) target).takeDamage(this.stats.getStrenght());
		}
	}

	@Override
	public <T> void magic(T target, int spellId) {
		if (target instanceof Enemie) {
			this.spells.castSpell(spellId, (Enemie) target);
		}
	}

	@Override
	public void defend() {
		if (this.stats.getHealth() < this.stats.getMaxHealth()) {
			this.stats.heal(super.rng(this.stats.getDefense(), 0));
			if (this.stats.getHealth() > this.stats.getMaxHealth()) {
				this.stats.setHealth(this.stats.getMaxHealth());
			}
		}
	}
	
	@Override
	public void takeDamage(int damage) {
		this.stats.damage(damage);
	}
	
	//
	
	public void equipItem(Item item, Entity target) {
		
		if (target instanceof Player) {
			
			if (item instanceof Armor || item instanceof Cloak) {
				
				if (this.armorEquiped != null) {
					try {
						this.inventory.addItem(this.armorEquiped);
					} catch (InventoryIsFullException e) {
						e.printStackTrace();
					}
				}
			
				this.armorEquiped = item;
				
			} else if (item instanceof Sword || item instanceof Staff) {
				if (this.weaponEquiped != null) {
					try {
						this.inventory.addItem(this.armorEquiped);
					} catch (InventoryIsFullException e) {
						e.printStackTrace();
					}
				}
				
				this.weaponEquiped = item;
				
			}
			
		} else if (target instanceof Teammate) {
			
			Teammate teammate = (Teammate) target;
			
			if (item instanceof Armor || item instanceof Cloak) {
				
				if (teammate.getArmorEquiped() != null) {
					try {
						this.inventory.addItem(teammate.getArmorEquiped());
					} catch (InventoryIsFullException e) {
						e.printStackTrace();
					}
				}
			
				teammate.setArmorEquiped(item);
				
			} else if (item instanceof Sword || item instanceof Staff) {
				if (teammate.getWeaponEquiped() != null) {
					try {
						this.inventory.addItem(teammate.getWeaponEquiped());
					} catch (InventoryIsFullException e) {
						e.printStackTrace();
					}
				}
				
				teammate.setArmorEquiped(item);
				
			}
			
		}
		
	}

	public void useItem(Item item, Entity target) {
		
	}
	
	public boolean checkHitbox(int npcX, int npcY) {

		int x = super.getX()-24;
		int y = super.getY()-24;
		
		if (npcX >= hitbox[0][0] + x && npcX <= hitbox[1][0] + x && 
			npcY >= hitbox[0][1] + y && npcY <= hitbox[1][1] + y) {
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
	
	public void setNpcs(Npc[] npcs) {
		this.npcs = npcs;
	}

	public Spells getSpells() {
		return spells;
	}

	public Inventory getInventory() {
		return inventory;
	}


	public Effects getEffects() {
		return effects;
	}
	
	public Collision getCollision() {
		return this.collision;
	}

	public Item getArmorEquiped() {
		return armorEquiped;
	}
	public Item getWeaponEquiped() {
		return weaponEquiped;
	}
	public void setArmorEquiped(Item armorEquiped) {
		this.armorEquiped = armorEquiped;
	}
	public void setWeaponEquiped(Item weaponEquiped) {
		this.weaponEquiped = weaponEquiped;
	}

	
	
}
