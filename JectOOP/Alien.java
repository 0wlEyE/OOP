import javax.swing.ImageIcon;
public class Alien extends Sprite {

    private Bomb bomb;

    ImageIcon img, ii;


    private final String alien = "img/Alien.png";

    public Alien(){}

    public Alien(int x, int y) {
        this.POS_X = x;
        this.POS_Y = y;

        bomb = new Bomb(x, y);
        img = new ImageIcon(this.getClass().getResource(alien));
        ii = new ImageIcon(getClass().getResource(alien));
        setImage(img.getImage());

    }

    public int gotShot(){
        System.out.println("die");
		this.setImage(ii.getImage());
		this.setDying(true);
        return 1;
    }

    public void act(int direction) {
        this.POS_X += direction;
    }
    
	public Bomb getBomb() {
		return bomb;
	}

}