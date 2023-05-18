import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Menu extends JPanel implements MouseListener {
    
    Rectangle Stage1 = new Rectangle(840 / 2 - 350, 450, 200, 100);
    Rectangle Stage2 = new Rectangle(840 / 2 - 100, 450, 200, 100);
    Rectangle Stage3 = new Rectangle(840 / 2 + 150, 450, 200, 100);
    JFrame fr = new JFrame("GAO Space");
    Sound sound = new Sound();

    Image background;

    public Menu() {

        ImageIcon ii = new ImageIcon("img/MenuBG.PNG");
        background = ii.getImage();
        sound.playMusic(0);

        fr.add(this);
        fr.setSize(840, 680);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
        fr.setVisible(true);
        fr.addMouseListener(this);

    }

    public void render(Graphics  g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("", Font.BOLD, 50));
        g2d.setColor(Color.white);
        g2d.drawString("Stage 1", 840 / 2 - 338, 500 + 18);
        g2d.drawString("Stage 2", 840 / 2 - 90, 500 + 18);
        g2d.drawString("Stage 3", 840 / 2 + 160, 500 + 18);
        g2d.draw(Stage1);
        g2d.draw(Stage2);
        g2d.draw(Stage3);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        render(g);

    }

    public static void main(String[] args) {
        new Menu();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (Stage1.contains(e.getX(), e.getY())) {
            fr.dispose();
            sound.stop();
            new FrameStage().createStage(1);
            System.out.println("1");
        } else if (Stage2.contains(e.getX(), e.getY())) {
            fr.dispose();   
            sound.stop();
            new FrameStage().createStage(2);
            System.out.println("2");
        } else if (Stage3.contains(e.getX(), e.getY())) {
            fr.dispose();
            sound.stop();
            new FrameStage().createStage(3);
            System.out.println("3");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
