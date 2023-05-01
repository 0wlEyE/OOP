import java.awt.*;
import javax.swing.*;


public class st1 extends JPanel{
    JFrame fr = new JFrame("GAO Space : Stage 1");
    Image background;
    
    public st1(){
        ImageIcon ii = new ImageIcon("img/bgnewnew.PNG");
        background = ii.getImage();

        fr.add(this);
        fr.setSize(840, 680);
        fr.getContentPane().add(this);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        g.drawImage(background, 0, 0, null);
    }
}
    // Image background;
    
    
    // public st1(){
    //     ImageIcon ii = new ImageIcon("bgnewnew.PNG");
    //     background = ii.getImage();
    //     System.out.println("kuy");
        
    // }

    // public void paint(Graphics g){
    //     super.paint(g);
    //     g.drawImage(background, 0, 0,  null);
    //     System.out.println("hee");
    // }


