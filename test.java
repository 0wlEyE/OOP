import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class test extends JPanel implements KeyListener {
public int px = 0, py = 0;
    public static void main(String[] args) {
        test p = new test();
        JFrame fr = new JFrame();
        fr.setSize(700, 700);
        fr.add(p);
        fr.addKeyListener(p);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 700);
        Image img = new ImageIcon("p1.gif").getImage();
        g.drawImage(img, this.px, this.py, this);
    }public void keyTyped(KeyEvent ke) { }
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == 37) { //Left
        this.px -= 10;
        } else if (ke.getKeyCode() == 38) { //Up
        this.py -= 10;
        } else if (ke.getKeyCode() == 39) { //Right
        this.px += 10;
        } else if (ke.getKeyCode() == 40) { //Down
        this.py += 10;
        }
        repaint();
    } public void keyReleased(KeyEvent ke) { }
}
