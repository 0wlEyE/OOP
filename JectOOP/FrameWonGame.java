import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class FrameWonGame extends JPanel implements MouseListener {

    private int numStage;
    private int posLeft;
    private int posRight;

    Rectangle retry, menu;
    JFrame fr;

    Image background;

    Sound sound = new Sound();
    
    //Constructure
    public FrameWonGame() {
        this(0);
    }

    public FrameWonGame(int num){
        sound.playMusic(8);
        numStage = num;

        posLeft = 840 / 2 - (200 + 80);
        posRight = 840 / 2 + 80;

        retry = new Rectangle(posLeft - 13, 450, 200, 100);
        menu = new Rectangle(posRight - 13, 450, 200, 100);
        fr = new JFrame("GAO Space");

        ImageIcon img = new ImageIcon("img/Win.png");
        background = img.getImage();
        
        //Add Object
        fr.add(this);
        fr.setSize(840, 680);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationRelativeTo(null);
        fr.setResizable(false);
        fr.setVisible(true);
        fr.addMouseListener(this);
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        if (numStage != 3){
            g2d.setFont(new Font("", Font.BOLD, 50));
            g2d.setColor(Color.WHITE);
            g2d.drawString("Next", (840 / 2) - (178 + 70), 500 + 18);
            g2d.drawString("Menu", (840 / 2) + 100, 500 + 18);
            g2d.draw(retry);
            g2d.draw(menu);
        } else if (numStage == 3) {
            menu = new Rectangle( (840 / 2) - 100, 450, 200, 100);
            g2d.setFont(new Font("", Font.BOLD, 50));
            g2d.setColor(Color.WHITE);
            g2d.drawString("Menu", (840 / 2) - 65, 500 + 18);
            g2d.draw(menu);
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (retry.contains(e.getX(), e.getY())) {
            sound.stop();
            fr.dispose();
            new FrameStage().createStage(numStage + 1);

        } else if (menu.contains(e.getX(), e.getY())) {
            sound.stop();
            fr.dispose();
            new Menu();
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
