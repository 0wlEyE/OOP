import javax.swing.ImageIcon;

/**
 * 
 * @author 
 */
public class WonGame extends Sprite implements DefaultCode{
    private final String won = "img/won.jpg";
    private int width;

    /*
     * Constructor
     */
    public WonGame() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(won));

        width = ii.getImage().getWidth(null); 

        setImage(ii.getImage());
        setX(0);
        setY(0);
    }

    /*
     * Getters & Setters
     */
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
