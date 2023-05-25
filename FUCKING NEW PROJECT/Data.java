import java.awt.*;

import javax.swing.*;

public class Data extends JInternalFrame{
    JLabel lab;
    JPanel panel;
    Wallet wallet;
    public Data(){
        new JInternalFrame();
        panel = new JPanel();
        wallet = new Wallet();
        
        panel.setLayout(getLayout());

        setBounds(90, 20,350 , 500);
        setVisible(true);

        lab = new JLabel(wallet.getBalance() + "");
        lab.setFont(new Font("Serif", Font.BOLD, 50));
        lab.setForeground(Color.GREEN);
        this.add(lab);
    }
}
