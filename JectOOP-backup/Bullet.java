import javax.swing.*;

public class Bullet extends Sprite {

    private String shot = "img/alreal.png";
    private final int H_SPACE = 12;
    private final int V_SPACE = 1;

    public Bullet() {
    }

    public Bullet(int x, int y) {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}