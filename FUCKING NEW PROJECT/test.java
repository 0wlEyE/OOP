import java.awt.*;
import javax.swing.*;
public class test {
    private JFrame fr;
    private JDesktopPane desktp;
    private JInternalFrame internalfr1, internalfr2, internalfr3;
    private JMenuBar mb;
    private JMenu m1, m2, m3, mn1;
    private JMenuItem mi1, mi2, mi3, mni1, mni2;
    public test(){
        // MenuBar
        fr = new JFrame("SubMenuItem Demo");
        mb = new JMenuBar();
        m1 = new JMenu("File"); m2 = new JMenu("Edit"); m3 = new JMenu("View");
        fr.setJMenuBar(mb);
        mb.add(m1); mb.add(m2); mb.add(m3);

        mn1 = new JMenu("New");
        mi1 = new JMenuItem("Open"); mi2 = new JMenuItem("Save"); mi3 = new JMenuItem("Exit");

        m1.add(mn1); m1.add(mi1); 
        m1.addSeparator();
        m1.add(mi2); 
        m1.addSeparator();
        m1.add(mi3);

        mni1 = new JMenuItem("Window"); mni2 = new JMenuItem("Message");
        mn1.add(mni1); 
        mn1.addSeparator();
        mn1.add(mni2);
        // MenuBar

        // DeskTopPane
        desktp = new JDesktopPane();
        internalfr1 = new JInternalFrame("Application 01", true, true, true, true);
        internalfr2 = new JInternalFrame("Application 02", true, true, true, true);
        internalfr3 = new JInternalFrame("Application 03", true, true, true, true);

        internalfr1.setSize(250, 250);;
        internalfr1.setVisible(true);

        internalfr2.setSize(250, 250);;
        internalfr2.setVisible(true);

        internalfr3.setSize(250, 250);;
        internalfr3.setVisible(true);
        // DeskTopPane

        // Location of each InternalFrame
        int x1 = internalfr1.getX() + internalfr1.getWidth()+15;
        int y1 = internalfr1.getY();
        internalfr2.setLocation(x1, y1);

        int x2 = internalfr2.getX() + internalfr2.getWidth()+15;
        int y2 = internalfr2.getY();
        internalfr3.setLocation(x2, y2);
        // Location of each InternalFrame

        desktp.add(internalfr1); desktp.add(internalfr2); desktp.add(internalfr3); 
        fr.add(desktp, BorderLayout.CENTER);
        fr.setMinimumSize(new Dimension(300, 300));
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
        desktp.setBackground(Color.BLACK);
        fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
    }  
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            e.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> { new test(); });
    }
}
