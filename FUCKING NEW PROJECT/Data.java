import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

public class Data extends JInternalFrame implements ActionListener, FocusListener, SaveLoadable {
    static JLabel balance, income, expense, ba_txt, in_txt, ex_txt;
    static ButtonGroup group;
    static JTextField amount, description;
    static Wallet wallet;
    static Double val;
    JRadioButton in_tick, ex_tick;
    JPanel data, action, ba_Lay, inEx_Lay, tick_Lay, amount_Lay, desc_Lay, con_Lay;
    JButton confirm;

    public Data(){
        new JInternalFrame();
        if (wallet == null){
            wallet = new Wallet(0.0, 0.0, 0.0);
        }
        //Upper Part     
        data = new JPanel(new GridLayout(2, 1));
        
        ba_Lay = new JPanel(new GridLayout(2, 1));
        inEx_Lay = new JPanel(new GridLayout(2, 2));

        ba_txt = new JLabel("Balance");
        ba_txt.setFont(new Font("Serif", Font.BOLD, 40));
        ba_txt.setForeground(Color.WHITE);
        ba_txt.setHorizontalAlignment(JLabel.CENTER);

        in_txt = new JLabel("Income");
        in_txt.setFont(new Font("Serif", Font.BOLD, 30));
        in_txt.setForeground(new Color(0, 230, 117));
        in_txt.setHorizontalAlignment(JLabel.CENTER);

        ex_txt = new JLabel("Expense");
        ex_txt.setFont(new Font("Serif", Font.BOLD, 30));
        ex_txt.setForeground(new Color(239, 83, 80));
        ex_txt.setHorizontalAlignment(JLabel.CENTER);

        balance = new JLabel(wallet.getBalance() + "");
        balance.setFont(new Font("Serif", Font.BOLD, 50));
        balance.setForeground(Color.WHITE);
        balance.setHorizontalAlignment(JLabel.CENTER);

        income = new JLabel(wallet.getIncome() + "");
        income.setFont(new Font("Serif", Font.BOLD, 40));
        income.setForeground(new Color(0, 230, 117));
        income.setHorizontalAlignment(JLabel.CENTER);

        expense = new JLabel(wallet.getIncome() + "");
        expense.setFont(new Font("Serif", Font.BOLD, 40));
        expense.setForeground(new Color(239, 83, 80));
        expense.setHorizontalAlignment(JLabel.CENTER);

        ba_Lay.setBackground(new Color(41, 50, 51));
        inEx_Lay.setBackground(new Color(41, 50, 51));

        ba_Lay.add(ba_txt);
        ba_Lay.add(balance);
        inEx_Lay.add(in_txt);
        inEx_Lay.add(ex_txt);
        inEx_Lay.add(income);
        inEx_Lay.add(expense);
        
        data.add(ba_Lay);
        data.add(inEx_Lay);

        //Lower Part
        action = new JPanel(new GridLayout(4, 1));
        tick_Lay = new JPanel(new FlowLayout());
        amount_Lay = new JPanel(new FlowLayout());
        desc_Lay = new JPanel(new FlowLayout());
        con_Lay = new JPanel(new FlowLayout());

        in_tick = new JRadioButton("Income", false);
        in_tick.setFont(new Font("Serif", Font.BOLD, 25));
        in_tick.setForeground(Color.WHITE);
        in_tick.setBackground(new Color(41, 50, 51));
        in_tick.setFocusPainted(false);

        ex_tick = new JRadioButton("Expense", false);
        ex_tick.setFont(new Font("Serif", Font.BOLD, 25));
        ex_tick.setForeground(Color.WHITE);
        ex_tick.setBackground(new Color(41, 50, 51));
        ex_tick.setFocusPainted(false);
        
        amount = new JTextField("Amount", 16);
        amount.setFont(new Font("Serif", Font.BOLD, 24));
        amount.setForeground(Color.WHITE);
        amount.setBackground(new Color(90,96,96));
        description = new JTextField("Description", 16);
        description.setFont(new Font("Serif", Font.BOLD, 24));
        description.setForeground(Color.WHITE);
        description.setBackground(new Color(90,96,96));

        confirm = new JButton("Confirm!");
        confirm.setFont(new Font("Serif", Font.BOLD, 24));
        confirm.setForeground(Color.WHITE);
        confirm.setBackground(new Color(0, 230, 117));
        confirm.setBorderPainted(false);
        confirm.setOpaque(true);
        con_Lay.add(confirm);
        con_Lay.setBackground(new Color(41, 50, 51));

        group = new ButtonGroup();
        group.add(in_tick); group.add(ex_tick);
        tick_Lay.add(in_tick); tick_Lay.add(ex_tick);
        tick_Lay.setBackground(new Color(41, 50, 51));
        
        amount_Lay.add(amount); desc_Lay.add(description);
        amount_Lay.setBackground(new Color(41, 50, 51));
        desc_Lay.setBackground(new Color(41, 50, 51));

        action.add(tick_Lay);
        action.add(amount_Lay); action.add(desc_Lay);
        action.add(con_Lay);

        action.setBackground(new Color(41, 50, 51));

        //Listener Part
        amount.addFocusListener(this);
        description.addFocusListener(this);
        confirm.addActionListener(this);
        
        setBounds(90, 20,350 , 500);
        setVisible(true);

        //Seperate Upper & Lower
        this.setLayout(new GridLayout(2, 1));
        this.add(data);
        this.add(action);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // press confirm
        if (e.getSource().equals(confirm)){
            // if amount != numberic
            try{
                val = Double.parseDouble(amount.getText());
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Please input only number!!", "Number Format Error", JOptionPane.ERROR_MESSAGE);
            }
            // if amount <= 0
            if(val < 0){
                JOptionPane.showMessageDialog(null, "Number must be larger than 0!!", "0 Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //if in & ex not selected
                if(in_tick.isSelected() == false && ex_tick.isSelected() == false){
                    JOptionPane.showMessageDialog(null, "Please select option!!", "Option not selected", JOptionPane.ERROR_MESSAGE);
                } 
                else{
                    //if Description is None
                    if (description.getText().equals("Description")){
                        description.setText("----------");
                    }
                    //if ex selected
                    if (ex_tick.isSelected()){
                        wallet.setBalance(wallet.getBalance() - val);
                        wallet.setExpense(wallet.getExpense() + val);
                        Table.setRow(description.getText(), "-" + amount.getText());
                    }
                    //if in selected
                    else if (in_tick.isSelected()){
                        wallet.setBalance(wallet.getBalance() + val);
                        wallet.setIncome(wallet.getIncome() + val);
                        Table.setRow(description.getText(), "+" + amount.getText());
                    }
                update();
                }
            Success.update();
            }
        }
    }
    //set data in table & return to begining
    public static void update(){
        Table.setTable(Table.getTable());
        balance.setText(wallet.getBalance() + "");
        income.setText(wallet.getIncome() + "");
        expense.setText(wallet.getExpense() + "");
        amount.setText("Amount");
        description.setText("Description");
        group.clearSelection();
        val = null;
    }
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource().equals(amount)){
            if(amount.getText().equals("Amount")){
                amount.setText("");
            }
        }
        if(e.getSource().equals(description)){
            if(description.getText().equals("Description")){
                description.setText("");
            }
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource().equals(amount)){
            if(amount.getText().length() == 0){
                amount.setText("Amount");
            }
        }
        if(e.getSource().equals(description)){
            if(description.getText().length() == 0){
                description.setText("Description");
            }
        }
    }

    @Override
    public void saveData() {
        try(FileOutputStream fout = new FileOutputStream("Wallet.dat");
                ObjectOutputStream oout = new ObjectOutputStream(fout);){
            oout.writeObject(wallet);
        }catch (IOException ex){}
    }

    @Override
    public void loadData() {
        try(FileInputStream fout = new FileInputStream("Wallet.dat");
                ObjectInputStream oout = new ObjectInputStream(fout);){
            wallet = (Wallet) oout.readObject();
            update();
        }catch (IOException ex){} 
        catch (ClassNotFoundException ex) {}
    }
}
