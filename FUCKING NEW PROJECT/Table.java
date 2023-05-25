import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class Table extends JInternalFrame{
    JLabel lab;
    public Table(){

        new JInternalFrame();
        setBounds(450, 20,350 , 500);
        setVisible(true);

        lab = new JLabel("aaa");
        this.add(lab);

    }
}
