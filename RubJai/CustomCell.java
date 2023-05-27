import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomCell extends DefaultTableCellRenderer{
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (row % 2 == 0) {
                component.setBackground(new Color(57,52,58));
            } else {
                component.setBackground(new Color(60,55,61));
            }
            component.setForeground(Color.WHITE);
            return component;
        }
    }
