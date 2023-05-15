import javax.swing.*;

public class Bomb extends Sprite {

	private final String bomb = "img/nbomb.png";
	private boolean destroyed;

	public Bomb(){}

	public Bomb(int x, int y) {
		setDestroyed(true);
		this.POS_X = x;
		this.POS_Y = y;
		ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
		setImage(ii.getImage());
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isDestroyed() {
		return destroyed;
	}
}