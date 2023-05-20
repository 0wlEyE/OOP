import javax.swing.*;

public class GameOver extends Sprite {

	private final String gameOver = "img/gameover.png";

	//Constructure
	public GameOver() {
		ImageIcon img = new ImageIcon(this.getClass().getResource(gameOver));

		setImage(img.getImage());
		setX(0);
		setY(0);
	}
}