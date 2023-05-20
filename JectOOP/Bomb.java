import javax.swing.*;

public class Bomb extends Sprite {

	private final String bomb = "img/nbomb.png";
	private boolean destroyed;

	//Constucture
	public Bomb(){}

	public Bomb(int x, int y) {
		setDestroyed(true);
		this.POS_X = x;
		this.POS_Y = y;
		ImageIcon img = new ImageIcon(this.getClass().getResource(bomb));
		setImage(img.getImage());
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
}