import javax.swing.ImageIcon;
public class AlienTanker extends Sprite {

    private int hp;

    ImageIcon img;

    private final String tanker = "img/Tanker.png";

    public AlienTanker(){}

    public AlienTanker(int x, int y) {
        hp = 3;

        this.POS_X = x;
        this.POS_Y = y;

        img = new ImageIcon(this.getClass().getResource(tanker));
        setImage(img.getImage());
    }

    public void act(int direction) {
        this.POS_X += direction;
    }

    public int setHP(int hp){
        this.hp = hp;
        if (this.hp <= 0){
            System.out.println("die");
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