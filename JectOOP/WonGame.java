import javax.swing.ImageIcon;

public class WonGame extends Sprite implements DefaultCode {
    private final String won = "img/won.jpg";

    public WonGame() {

        ImageIcon img = new ImageIcon(this.getClass().getResource(won));

        setImage(img.getImage());
        setX(0);
        setY(0);
        
    }
}