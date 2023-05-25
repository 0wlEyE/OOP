import java.awt.*;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.table.*;

public class Table extends JInternalFrame{
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;

    public Table(){
        new JInternalFrame();
        LocalDate time = LocalDate.now();

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 350, 500);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.addColumn("Date"); 
        model.addColumn("Information");
        model.addColumn("Amount");
        for(int i=0;i <= 144; i++) {
            model.addRow(new Object[0]);
            if (i%2 == 0){
                model.setValueAt(time, i, 0);
                model.setValueAt("Eating", i, 1);   
                model.setValueAt("12000", i, 2);
            }else{
                model.setValueAt(time, i, 0);
                model.setValueAt("Nothing", i, 1);
                model.setValueAt("21000", i, 2);
            }
        }
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        add(panel);
        setBounds(450, 20,350 , 500);
        setVisible(true);
    }
}
