import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RaiRubRaiJai implements ActionListener{
    JFrame frame;
    JDesktopPane desktp;
    JButton b1;
    Data data;
    Table table;
    JMenuItem refresh;
    JMenuBar bar;
    
    public RaiRubRaiJai(){
        //Create Object
        frame = new JFrame("RaiRubRaiJai");
        desktp = new JDesktopPane();
        refresh = new JMenuItem("Refresh");
        bar = new JMenuBar();
        data = new Data();
        table = new Table();
        frame.setJMenuBar(bar);
        
        refresh.addActionListener(this);
        bar.add(refresh);
        desktp.add(data);
        desktp.add(table);
        frame.add(desktp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        desktp.setBackground(Color.LIGHT_GRAY);
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> { new RaiRubRaiJai(); });
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(refresh)){
            data.setLocation(90, 20);
            table.setLocation(450, 20);
        }
    }
}
