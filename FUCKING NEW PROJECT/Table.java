import java.awt.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.*;

public class Table extends JInternalFrame{
    private JPanel panel;
    private JScrollPane scrollPane;
    private static JTable table;
    private static LocalDate time = LocalDate.now();
    private static int row = 0;

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

        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        add(panel);
        setBounds(450, 20,350 , 500);
        setVisible(true);
    }
    public static void setRow(String desc, String inEx){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.addRow(new Object[0]);
        model.setValueAt(time, row, 0);
        model.setValueAt(desc, row, 1);   
        model.setValueAt(inEx, row, 2);
        row += 1;
    }
    public void setTable(JTable tab){
        table = tab;
    }
    public JTable getTable(){
        return table;
    }
}