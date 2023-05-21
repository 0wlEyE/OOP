import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Player extends Sprite implements DefaultCode, KeyListener{

	private final int START_Y = 600;
	private final int START_X = 470;

	private final String player = "img/Player.PNG";
	ImageIcon img;

	private int width;

	//Constructure
	public Player() {
		img = new ImageIcon(this.getClass().getResource(player));

		width = img.getImage().getWidth(null);

		setImage(img.getImage());
		setX(START_X);
		setY(START_Y);
	}

	public void act() {
		POS_X += direction;
		if (POS_X <= 13)
			POS_X = 13;
		if (POS_X >= BOARD_WIDTH - 1.5 * width)
			POS_X = (int )(BOARD_WIDTH - 1.5 * width);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			direction = -4;
		}

		if (key == KeyEvent.VK_RIGHT) {
			direction = 4;
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			direction = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			direction = 0;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void destroyed(){
		this.setDying(true);
	}
}