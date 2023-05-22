import javax.swing.*;

public class Bullet extends Sprite {

    private String shot = "img/Player_Shot.png";
    private final int bulletX = 12;
    private final int bulletY = 1;

    //Constucture
    public Bullet() {}

    public Bullet(int x, int y) {

        ImageIcon img = new ImageIcon(this.getClass().getResource(shot));
        setImage(img.getImage());
        setX(x + bulletX);
        setY(y - bulletY);
    }
}