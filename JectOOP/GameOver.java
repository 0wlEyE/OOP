import javax.swing.*;

public class GameOver extends Sprite {

	private final String gameOver = "img/gameover.png";
	private int width;

	//Constructure
	public GameOver() {
		ImageIcon img = new ImageIcon(this.getClass().getResource(gameOver));

		setWidth(img.getImage().getWidth(null));

		setImage(img.getImage());
		setX(0);
		setY(0);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}