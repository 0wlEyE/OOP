import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
public class Player extends Sprite implements Commons {

	private final int START_Y = 400;
	private final int START_X = 270;

	private final String player = "craft.png";
	private int width;

	public Player() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource(player));

		width = ii.getImage().getWidth(null);

		setImage(ii.getImage());
		setX(START_X);
		setY(START_Y);
	}

	public void act() {
		POS_X += dx;
		if (POS_X <= 2)
			POS_X = 2;
		if (POS_X >= BOARD_WIDTH - 2 * width)
			POS_X = BOARD_WIDTH - 2 * width;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == 65) {
			dx = -2;
		}

		if (key == 68) {
			dx = 2;
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == 65) {
			dx = 0;
		}

		if (key == 68) {
			dx = 0;
		}
	}
}
