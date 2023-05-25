import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Data extends JInternalFrame implements ActionListener, FocusListener {
    JLabel balance, income, expense, ba_txt, in_txt, ex_txt;
    JRadioButton in_tick, ex_tick;
    ButtonGroup group;
    JPanel data, action, ba_Lay, inEx_Lay, tick_Lay, amount_Lay, desc_Lay, con_Lay;
    JTextField amount, description;
    Wallet wallet;
    JButton confirm;
    Double value;
    public Data(){
        new JInternalFrame();
        wallet = new Wallet(0.0, 0.0, 0.0);

        //Upper Part     
        data = new JPanel(new GridLayout(2, 1));
        
        ba_Lay = new JPanel(new GridLayout(2, 1));
        inEx_Lay = new JPanel(new GridLayout(2, 2));

        ba_txt = new JLabel("Balance");
        ba_txt.setFont(new Font("Serif", Font.BOLD, 40));
        ba_txt.setHorizontalAlignment(JLabel.CENTER);

        in_txt = new JLabel("Income");
        in_txt.setFont(new Font("Serif", Font.BOLD, 30));
        in_txt.setForeground(Color.GREEN);
        in_txt.setHorizontalAlignment(JLabel.CENTER);

        ex_txt = new JLabel("Expense");
        ex_txt.setFont(new Font("Serif", Font.BOLD, 30));
        ex_txt.setForeground(Color.RED);
        ex_txt.setHorizontalAlignment(JLabel.CENTER);

        balance = new JLabel(wallet.getBalance() + "");
        balance.setFont(new Font("Serif", Font.BOLD, 50));
        balance.setHorizontalAlignment(JLabel.CENTER);

        income = new JLabel(wallet.getIncome() + "");
        income.setFont(new Font("Serif", Font.BOLD, 40));
        income.setForeground(Color.GREEN);
        income.setHorizontalAlignment(JLabel.CENTER);

        expense = new JLabel(wallet.getIncome() + "");
        expense.setFont(new Font("Serif", Font.BOLD, 40));
        expense.setForeground(Color.RED);
        expense.setHorizontalAlignment(JLabel.CENTER);

        ba_Lay.setBackground(new Color(0, 128, 255));
        inEx_Lay.setBackground(new Color(0, 128, 255));

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
        in_tick.setBackground(new Color(0, 128, 255));
        in_tick.setFocusPainted(false);

        ex_tick = new JRadioButton("Expense", false);
        ex_tick.setFont(new Font("Serif", Font.BOLD, 25));
        ex_tick.setBackground(new Color(0, 128, 255));
        ex_tick.setFocusPainted(false);
        
        amount = new JTextField("Amount", 16);
        amount.setFont(new Font("Serif", Font.BOLD, 24));
        description = new JTextField("Description", 16);
        description.setFont(new Font("Serif", Font.BOLD, 24));

        confirm = new JButton("Confirm!");
        confirm.setFont(new Font("Serif", Font.BOLD, 24));
        con_Lay.add(confirm);
        con_Lay.setBackground(new Color(0, 128, 255));

        group = new ButtonGroup();
        group.add(in_tick); group.add(ex_tick);
        tick_Lay.add(ex_tick); tick_Lay.add(in_tick);
        tick_Lay.setBackground(new Color(0, 128, 255));
        
        amount_Lay.add(amount); desc_Lay.add(description);
        amount_Lay.setBackground(new Color(0, 128, 255));
        desc_Lay.setBackground(new Color(0, 128, 255));

        action.add(tick_Lay);
        action.add(amount_Lay); action.add(desc_Lay);
        action.add(con_Lay);

        action.setBackground(new Color(0, 128, 255));

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

    public Wallet getWallet(){
        return wallet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // press confirm
        if (e.getSource().equals(confirm)){
            // if amount != numberic
            try{
                value = Double.parseDouble(amount.getText());
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Please input only number!!", "Number Format Error", JOptionPane.ERROR_MESSAGE);
            }
            // if amount <= 0
            if(value <= 0){
                JOptionPane.showMessageDialog(null, "Number must be larger than 0!!", "0 Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //if in & ex not selected
                if(in_tick.isSelected() == false && ex_tick.isSelected() == false){
                    JOptionPane.showMessageDialog(null, "Please select option!!", "Option not selected", JOptionPane.ERROR_MESSAGE);
                } 
                else{
                    //if ex selected
                    if (ex_tick.isSelected()){
                        wallet.setBalance(wallet.getBalance() - value);
                        wallet.setExpense(wallet.getExpense() + value);
                    }
                    //if in selected
                    else if (in_tick.isSelected()){
                        wallet.setBalance(wallet.getBalance() + value);
                        wallet.setIncome(wallet.getIncome() + value);
                    }
                    if(description.getText().equals("Description")){
                        description.setText("----------");
                    }
                    update();
                }
            }
        }
    }
    //set data in table & return to begining
    public void update(){
        Table.setRow(description.getText(), amount.getText());
        balance.setText(wallet.getBalance() + "");
        income.setText(wallet.getIncome() + "");
        expense.setText(wallet.getExpense() + "");
        amount.setText("Amount");
        description.setText("Description");
        group.clearSelection();
        value = null;
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
}
