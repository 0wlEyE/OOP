import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.time.LocalDate;

public class Table extends JInternalFrame implements SaveLoadable{
    private static LocalDate time = LocalDate.now();
    private static int row;
    private static JTable table;
    private JPanel panel;
    private JScrollPane scrollPane;

    public Table(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 350, 390);
        add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setDefaultRenderer(Object.class, new CustomCell());

        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.addColumn("Date"); 
        model.addColumn("Information");
        model.addColumn("Amount");

        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        add(panel);
        setBounds(450, 20,350 , 390);
        setVisible(true);
    }
    public static void setTable(JTable tab){
        table = tab;
    }
    public static JTable getTable(){
        return table;
    }
    public static void setRow(String desc, String inEx){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        row = table.getRowCount();
        model.addRow(new Object[0]);
        model.setValueAt(time, row, 0);
        model.setValueAt(desc, row, 1);   
        model.setValueAt(inEx, row, 2);
        row += 1;
    }
    @Override
    public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("save/Table.txt") );){
            for (int i = 0; i < Table.getTable().getRowCount(); i++){
                for (int j = 0; j < Table.getTable().getColumnCount(); j++){
                    bw.write(table.getValueAt(i, j).toString() + " ");
                }
                bw.newLine();
            }
        } catch (IOException ex){}
    }
    @Override
    public void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("save/Table.txt") );){
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] lines = br.lines().toArray();
            for (int i = 0; i < lines.length; i++){
                String[] row = lines[i].toString().split(" ");
                model.addRow(row);
            }
        } catch (IOException ex){}
    }
}