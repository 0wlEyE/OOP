import javax.swing.ImageIcon;
public class AlienTanker extends Sprite {

    private Bomb bomb;
    private final String tanker = "img/Tanker.png";

    public AlienTanker(){}

    public AlienTanker(int x, int y) {
        this.POS_X = x;
        this.POS_Y = y;

        bomb = new Bomb(x, y);
        ImageIcon img = new ImageIcon(this.getClass().getResource(tanker));
        setImage(img.getImage());

    }

    public void act(int direction) {
        this.POS_X += direction;
    }
    
	public Bomb getBomb() {
		return bomb;
	}

}