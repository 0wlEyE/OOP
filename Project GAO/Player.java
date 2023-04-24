import java.awt.event.*;
import javax.swing.*;
public class Player implements KeyListener{
    private final int POSY_START = 400;
    private final int POSX_START = 270;
    private final String player = "craft.png";
    public Player(){
        
    }
    public void keyPressed(KeyEvent ke){
        if (ke.getKeyCode() == 65){ //LEFT 65 RIGHT 68
            
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
