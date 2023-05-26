import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class RaiRubRaiJai extends RubJaiWindow implements ActionListener, Serializable{
    JFrame frame;
    JDesktopPane desktp;
    JButton b1;
    Data data;
    Table table;
    Success success;
    JMenuItem refresh;
    JMenuBar bar;
    Wallet wallet = new Wallet();
    JTable tab;
    
    public RaiRubRaiJai(){
        //Create Object
        frame = new JFrame("RaiRubRaiJai");
        desktp = new JDesktopPane();
        refresh = new JMenuItem("Refresh");
        bar = new JMenuBar();
        data = new Data();
        table = new Table();
        success = new Success();
        frame.setJMenuBar(bar);
        
        refresh.addActionListener(this);
        frame.addWindowListener(this);

        bar.add(refresh);
        desktp.add(data);
        desktp.add(table);
        desktp.add(success);
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
            success.setLocation(450, 420);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        data.loadData();
        table.loadData();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        data.saveData();
        table.saveData();
    }
}
