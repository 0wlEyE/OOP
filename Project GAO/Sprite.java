import java.awt.*;

public class Sprite {
    private boolean visible;
    private Image img;
    protected int x;
    protected int y;
    protected int position;
    protected boolean dead;

    public Sprite() {
    }

    public void alive() {
        visible = true;
    }

    public void die() {
        visible = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible(){
        return visible;
    }

    public void setImage(Image img){
        this.img = img;
    }

    public Image getImage(){
        return img;
    }

    public void setDead(boolean dead){
        this.dead = dead;
    }

    public boolean getDead(){
        return dead;
    }
}
