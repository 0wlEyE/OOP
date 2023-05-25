import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class RaiRubRaiJai implements ActionListener, WindowListener{
    JFrame frame;
    JDesktopPane desktp;
    JButton b1;
    Data data;
    Table table;
    JMenuItem refresh;
    JMenuBar bar;
    Wallet wallet;
    JTable tab;
    
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
    @Override
    public void windowOpened(WindowEvent e) {
        try(FileInputStream fout = new FileInputStream("Wallet.dat");
                ObjectInputStream oout = new ObjectInputStream(fout);){
            wallet = (Wallet) oout.readObject();
            data.getWallet().setBalance(wallet.getBalance());
            data.getWallet().setIncome(wallet.getIncome());
            data.getWallet().setExpense(wallet.getExpense());
        }catch (IOException ex){} 
        catch (ClassNotFoundException ex) {}

        try(FileInputStream fout = new FileInputStream("Table.dat");
                ObjectInputStream oout = new ObjectInputStream(fout);){
            tab = (JTable) oout.readObject();
            table.setTable(tab);
        }catch (IOException ex){}
        catch (ClassNotFoundException ex) {}
    }
    @Override
    public void windowClosing(WindowEvent e) {
        try(FileOutputStream fout = new FileOutputStream("Wallet.dat");
                ObjectOutputStream oout = new ObjectOutputStream(fout);){
            // wallet.setBalance(data.getWallet().getBalance());
            // wallet.setIncome(data.getWallet().getIncome());
            // wallet.setExpense(data.getWallet().getExpense());
            System.out.println(data.getWallet().getBalance());
            oout.writeObject(data.getWallet());
        }catch (IOException ex){}

        try(FileOutputStream fout = new FileOutputStream("Table.dat");
                ObjectOutputStream oout = new ObjectOutputStream(fout);){
            tab = table.getTable();
            oout.writeObject(tab);
        }catch (IOException ex){}
    }
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
