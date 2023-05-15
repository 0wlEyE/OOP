import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Sprite implements DefaultCode {

	private final int START_Y = 600;
	private final int START_X = 470;

	private final String player = "img/Player.PNG";
	private final String expl = "img/explosion.png";
	ImageIcon ii, II;

	private int width;

	public Player() {
		ii = new ImageIcon(this.getClass().getResource(player));

		width = ii.getImage().getWidth(null);

		setImage(ii.getImage());
		setX(START_X);
		setY(START_Y);
	}

	public void act() {
		POS_X += dx;
		if (POS_X <= 13)
			POS_X = 13;
		if (POS_X >= BOARD_WIDTH - 1.5 * width)
			POS_X = (int )(BOARD_WIDTH - 1.5 * width);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -3;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 3;
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public void destroyed(){
		ImageIcon ii = new ImageIcon(this.getClass().getResource(expl));
		this.setImage(ii.getImage());
		this.setDying(true);
	}
}
