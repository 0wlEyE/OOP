import javax.swing.*;

public class FrameStage1{
    JFrame frame = new JFrame("GAO Space : Stage 1");

    public FrameStage1(){
        frame.add(new Stage1());
        frame.setSize(840, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}