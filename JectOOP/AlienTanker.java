import javax.swing.ImageIcon;
public class AlienTanker extends Sprite {

    private int hp;
    private Bomb bomb;

    ImageIcon img, II;

    private final String tanker = "img/Tanker.png";
    private final String expl = "img/explosion.png";

    public AlienTanker(){}

    public AlienTanker(int x, int y) {
        hp = 3;

        this.POS_X = x;
        this.POS_Y = y;

        bomb = new Bomb(x, y);
        img = new ImageIcon(this.getClass().getResource(tanker));
        II = new ImageIcon(getClass().getResource(expl));
        setImage(img.getImage());

    }

    public void act(int direction) {
        this.POS_X += direction;
    }
    
	public Bomb getBomb() {
		return bomb;
	}

    public int setHP(int hp){
        this.hp = hp;
        if (this.hp <= 0){
            System.out.println("die");
				this.setImage(II.getImage());
				this.setDying(true);
            return 1;
        }else{
            return 0;
        }
    }

    public int getHP(){
        return this.hp;
    }
}