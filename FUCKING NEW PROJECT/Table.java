import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Table extends JInternalFrame{
    private JPanel panel;
    private JTable table;
    private JScrollPane scrollPane;

    public Table(){
        new JInternalFrame();

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
                model.setValueAt("12/01/2023", i, 0);
                model.setValueAt("Eating", i, 1);
                model.setValueAt("12000", i, 2);
            }else{
                model.setValueAt("21/10/2032", i, 0);
                model.setValueAt("Nothing", i, 1);
                model.setValueAt("21000", i, 2);
            }
        }

        add(panel);
        setBounds(450, 20,350 , 500);
        setVisible(true);
    }
}
