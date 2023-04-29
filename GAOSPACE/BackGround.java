import javax.swing.ImageIcon;

public class BackGround extends Sprite{
    private final String bg = "bgnew.png";
    private int width;

    /*
     * Constructor
     */
    public BackGround() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(bg));

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