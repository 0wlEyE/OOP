import javax.swing.ImageIcon;

public class BackGround extends Sprite{
    private final String bg = "img/InGameBG.PNG";
    private int width;

    //Constucture
    public BackGround() {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(bg));

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
