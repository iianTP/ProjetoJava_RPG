package Entities;

import java.awt.image.BufferedImage;

public class Entity {
	
	// Coordenadas e velocidade
	public int x, y;
	public int walkSpeed;
	
	// Sprites
	public BufferedImage idleUp, idleDown, idleRight, idleLeft;
	public BufferedImage up1, down1, right1, left1;
	public BufferedImage up2, down2, right2, left2;
	
	// Direção
	public String facing;
	
	// Parado ou andando
	public boolean idle;
}
