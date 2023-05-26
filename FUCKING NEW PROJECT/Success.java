import java.awt.*;
import javax.swing.*;

public class Success extends JInternalFrame{
    private JPanel panel;
    private JLabel txt;
    private JProgressBar bar;

    public Success() {
        panel = new JPanel();
        bar = new JProgressBar();

        txt = new JLabel("Achievement Goal");
        txt.setFont(new Font("Serif", Font.PLAIN, 20));

        bar.setMinimum(0);
        bar.setMaximum(100);
        bar.setStringPainted(true);
        bar.setValue(0);
        bar.setPreferredSize(new Dimension(300, 30));
        
        panel.add(bar);
        panel.add(txt);
        this.add(panel);
        this.setBounds(450, 420, 350, 100);
        this.setVisible(true);
    }
}
