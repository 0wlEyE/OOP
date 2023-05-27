import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Success extends JInternalFrame implements SaveLoadable{
    private static int value = 0;
    private static double price;
    private static String want = " ";
    private static JProgressBar bar;
    private static JLabel txt;
    private JPanel panel;
    static ImageIcon goal = new ImageIcon("img/Goal.png");

    public Success() {
        panel = new JPanel();
        panel.setBackground(new Color(41, 50, 51));
        bar = new JProgressBar(0, 100);

        txt = new JLabel("Goal : " + want);
        txt.setFont(new Font("Serif", Font.PLAIN, 20));
        txt.setForeground(Color.WHITE);

        bar.setStringPainted(true);
        bar.setValue(0);
        bar.setForeground(new Color(0, 230, 117));
        bar.setPreferredSize(new Dimension(300, 30));
        
        panel.add(bar);
        panel.add(txt);

        this.add(panel);
        this.setBounds(450, 420, 350, 100);
        this.setVisible(true);
    }
    public static void setWant(String text){
        want = text;
    }
    public static void setPrice(double val){
        price = val;
    }
    public static int getValue() {
        return value;
    }
    public static void setValue(int value) {
        Success.value = value;
    }
    public static void update(){
        txt.setText("Goal : " + want);
        if (price == 0){
            value = 0;
        } else {
            value = (int) (100 - (((price - Data.wallet.getBalance()) / price) * 100));
        }
        bar.setValue(value);
        if (value >= 100){
            JOptionPane.showMessageDialog(null, "Your reached your goal.", "Congratulation!!", JOptionPane.PLAIN_MESSAGE, goal);
        }
    }
    @Override
    public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("save/Want.txt") );){
            bw.write(want + " ");
        } catch (IOException ex){}

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("save/Price.txt") );){
            bw.write(price + "");
        } catch (IOException ex){}
    }
    @Override
    public void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader("save/Want.txt") );){
            want = br.readLine();
            update();
        } catch (IOException ex){}

        try (BufferedReader br = new BufferedReader(new FileReader("save/Price.txt") );){
            price = Double.parseDouble(br.readLine());
            txt.setText("Goal : " + want);
            if (price == 0){
                value = 0;
            } else {
                value = (int) (100 - (((price - Data.wallet.getBalance()) / price) * 100));
            }
            bar.setValue(value);
        } catch (IOException ex){}
    }
}
