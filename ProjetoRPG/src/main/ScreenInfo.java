package main;

public class ScreenInfo {

	private int tileSide = 48; // Texturas 48px x 48px
	private int screenSide = 720; // Dimensões da tela 720px x 720px
	private int tilesPerSide = screenSide/tileSide;
	
	public int tileSide() {
		return this.tileSide;
	}
	public int screenSide() {
		return this.screenSide;
	}
	public int tilesPerSide() {
		return this.tilesPerSide;
	}
	
}
