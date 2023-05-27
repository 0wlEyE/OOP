import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GoalFrame extends MyWindow implements ActionListener, SaveLoadable{
    static Goal goal;
    JFrame frame;
    JPanel panel1, panel2, panel_button, panel_name, panel_money;
    JTextField name, money;
    JButton save, buy, clear;
    JLabel name_txt, money_txt;
    double value;

    public GoalFrame(){
        //Create Object
        frame = new JFrame("Planning");
        frame.setLayout(new GridLayout(5, 1));
        if (goal == null){
            goal = new Goal("", 0.0);
        }
        
        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel_button = new JPanel(new FlowLayout());
        panel_name = new JPanel(new FlowLayout());
        panel_money = new JPanel(new FlowLayout());

        panel1.setBackground(new Color(41, 50, 51));
        panel2.setBackground(new Color(41, 50, 51));
        panel_button.setBackground(new Color(41, 50, 51));
        panel_name.setBackground(new Color(41, 50, 51));
        panel_money.setBackground(new Color(41, 50, 51));

        name = new JTextField(11);
        name.setFont(new Font("Serif", Font.BOLD, 22));
        name.setText(goal.getGoal_name());
        name.setForeground(Color.WHITE);
        name.setBackground(new Color(90,96,96));

        money = new JTextField(11);
        money.setFont(new Font("Serif", Font.BOLD, 22));
        money.setText(goal.getPrice() + "");
        money.setForeground(Color.WHITE);
        money.setBackground(new Color(90,96,96));

        save = new JButton("Save");
        save.setForeground(Color.WHITE);
        save.setBackground(new Color(0, 230, 117));
        save.setBorderPainted(false);

        buy = new JButton("Purchase");
        buy.setForeground(Color.WHITE);
        buy.setBackground(new Color(0, 230, 117));
        buy.setBorderPainted(false);

        clear = new JButton("Clear");
        clear.setForeground(Color.WHITE);
        clear.setBackground(new Color(0, 230, 117));
        clear.setBorderPainted(false);

        name_txt = new JLabel("  Thing you want");
        name_txt.setFont(new Font("Serif", Font.BOLD, 20));
        name_txt.setForeground(new Color(0, 230, 117));
        
        money_txt = new JLabel("  Price");
        money_txt.setFont(new Font("Serif", Font.BOLD, 20));
        money_txt.setForeground(new Color(0, 230, 117));

        panel1.add(name_txt);
        panel2.add(money_txt);
        panel_button.add(save);
        panel_button.add(buy);
        panel_button.add(clear);
        panel_name.add(name);
        panel_money.add(money);
        
        frame.add(panel1, BorderLayout.WEST);
        frame.add(panel_name);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel_money);
        frame.add(panel_button);

        save.addActionListener(this);
        buy.addActionListener(this);
        clear.addActionListener(this);
        frame.addWindowListener(this);
        
        buy.setEnabled(false);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(250, 250);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    @Override
    public void saveData() {
        try (FileOutputStream fout = new FileOutputStream("save/Goal.dat");
                ObjectOutputStream oout = new ObjectOutputStream(fout);){
            goal.setGoal_name(name.getText());
            goal.setPrice(Double.parseDouble(money.getText()));
            oout.writeObject(goal);
        } catch (IOException ex){}
    }
    @Override
    public void loadData() {
        try (FileInputStream fout = new FileInputStream("save/Goal.dat");
                ObjectInputStream oout = new ObjectInputStream(fout);){
            goal = (Goal) oout.readObject();
            name.setText(goal.getGoal_name());
            money.setText(goal.getPrice() + "");
        } catch (IOException ex){} catch (ClassNotFoundException ex) {}
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(save)){
            try {
                value = Double.parseDouble(money.getText());
                if (value < 0){
                    JOptionPane.showMessageDialog(null, "Number must be larger than 0!!", "Lower than 0 Error", JOptionPane.ERROR_MESSAGE);
                } else if (name.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please input your wanted!!", "Name Error.", JOptionPane.ERROR_MESSAGE);
                } else if (value <= 0){
                    JOptionPane.showMessageDialog(null, "Price must be larger than 0!!", "Lower than 0 Error.", JOptionPane.ERROR_MESSAGE);
                } else {
                    saveData();
                    Success.setWant(name.getText());
                    Success.setPrice(Double.parseDouble(money.getText()));
                    Success.update();
                    frame.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please input only number!!", "Number Format Error.", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource().equals(buy)){
            Table.setRow(name.getText(), "-" + money.getText());
            Data.wallet.setBalance(Data.wallet.getBalance() - Double.parseDouble(money.getText()));
            Data.wallet.setExpense(Data.wallet.getExpense() + Double.parseDouble(money.getText()));
            Data.update();
            updateBar();
            JOptionPane.showMessageDialog(null, "Purchase success.","", JOptionPane.PLAIN_MESSAGE, Success.goal);
            name.setText("");
            money.setText("0.0");
            buy.setEnabled(false);
        } else if (e.getSource().equals(clear)){
            updateBar();
            name.setText("");
            money.setText("0.0");
            buy.setEnabled(false);
        }
    }
    @Override
    public void windowOpened(WindowEvent e) {
        loadData();
        if (Success.getValue() >= 100){
            buy.setEnabled(true);
        }
    }
    @Override
    public void windowClosing(WindowEvent e) {
        if (money.getText().equals("")){
            money.setText("0.0");
        }
        saveData();
    }
    public static Goal getGoal(){
        return goal;
    }
}
