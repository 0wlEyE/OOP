import javax.swing.ImageIcon;

public class BackGround extends Sprite{
    private final String bg = "img/InGameBG.PNG";

    //Constucture
    public BackGround() {

        ImageIcon img = new ImageIcon(this.getClass().getResource(bg));

        setImage(img.getImage());
        setX(0);
        setY(0);
    }
}
