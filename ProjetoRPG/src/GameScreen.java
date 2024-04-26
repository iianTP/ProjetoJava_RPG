
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class GameScreen extends JPanel {
	
	private int textureResolution = 48; // texturas 48px x 48px
	private int screenSide = 720; // dimensões da tela 720px x 720px
	
	public GameScreen() {
		this.setPreferredSize(new Dimension(screenSide, screenSide));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
	}
	
}
