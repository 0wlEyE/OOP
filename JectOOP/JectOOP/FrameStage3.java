import javax.swing.*;

public class FrameStage3{
    JFrame frame = new JFrame("GAO Space : Stage 3");

    public FrameStage3(){
        frame.add(new Stage3());
        frame.setSize(840, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
}
