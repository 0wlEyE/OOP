import javax.swing.ImageIcon;
public class Alien extends Sprite {

    private Bomb bomb;

    ImageIcon img;

    private final String alien = "img/Alien.png";

    private Sound sound;

    //Constucture
    public Alien(){}

    public Alien(int x, int y) {
        this.POS_X = x;
        this.POS_Y = y;

        bomb = new Bomb(x, y);
        img = new ImageIcon(this.getClass().getResource(alien));
        setImage(img.getImage());
        sound = new Sound();

    }

    public int gotShot(){
        sound.playSound(9);
		this.setImage(img.getImage());
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
