import javax.swing.ImageIcon;
public class Alien extends Sprite {

    private Bomb bomb;
    private final String alien = "img/Alien.png";

    public Alien(){}

    public Alien(int x, int y) {
        this.POS_X = x;
        this.POS_Y = y;

        bomb = new Bomb(x, y);
        ImageIcon img = new ImageIcon(this.getClass().getResource(alien));
        setImage(img.getImage());

    }

    public void act(int direction) {
        this.POS_X += direction;
    }
    
	public Bomb getBomb() {
		return bomb;
	}

}