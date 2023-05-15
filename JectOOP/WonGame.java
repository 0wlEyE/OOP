import javax.swing.ImageIcon;

public class WonGame extends Sprite implements DefaultCode{
    private final String won = "img/won.jpg";
    private int width;

    public WonGame() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(won));

        width = ii.getImage().getWidth(null); 

        setImage(ii.getImage());
        setX(0);
        setY(0);
    }

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}