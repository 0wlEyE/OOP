import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RaiRubRaiJai extends MyWindow implements ActionListener, Serializable{
    JFrame frame;
    JDesktopPane desktp;
    JButton b1;
    Data data;
    Table table;
    JMenuItem refresh, plan, reset;
    Success success;
    JMenu menu;
    JMenuBar bar;
    Wallet wallet;
    JTable tab;
    
    public RaiRubRaiJai(){
        //Create Object
        frame = new JFrame("RaiRubRaiJai");
        desktp = new JDesktopPane();
        refresh = new JMenuItem("Refresh");
        plan = new JMenuItem("Plan");
        reset = new JMenuItem("Reset");
        bar = new JMenuBar();
        data = new Data();
        table = new Table();
        menu = new JMenu("Menu");
        wallet = new Wallet();
        success = new Success();
        frame.setJMenuBar(bar);
        
        refresh.addActionListener(this);
        plan.addActionListener(this);
        reset.addActionListener(this);
        frame.addWindowListener(this);

        menu.add(refresh);
        menu.addSeparator();
        menu.add(plan);
        menu.addSeparator();
        menu.add(reset);
        bar.add(menu);

        desktp.add(data);
        desktp.add(table);
        desktp.add(success);
        frame.add(desktp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        desktp.setBackground(new Color(26, 30, 30));
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
        }else if (e.getSource().equals(plan)){
            new GoalFrame();
        }else if (e.getSource().equals(reset)){
            int dialoguebutton = JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING!!!", JOptionPane.YES_NO_OPTION);
            if (dialoguebutton == JOptionPane.YES_OPTION){
                Data.wallet.setBalance(0);
                Data.wallet.setExpense(0);
                Data.wallet.setIncome(0);
                Data.update();
                updateBar();
                DefaultTableModel model = (DefaultTableModel) Table.getTable().getModel();
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--){
                    model.removeRow(i);
                }
                JOptionPane.showMessageDialog(null, "You have deleted data", "", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {
        JOptionPane.showMessageDialog(null, "Welcome to RaiRubRaiJai.","", JOptionPane.PLAIN_MESSAGE);
        data.loadData();
        table.loadData();
        success.loadData();
        Success.update();
    }
    @Override
    public void windowClosing(WindowEvent e) {
        data.saveData();
        table.saveData();
        success.saveData();
    }
}
