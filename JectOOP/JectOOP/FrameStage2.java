import javax.swing.*;

public class FrameStage2 extends StageSelect{
    JFrame frame = new JFrame("GAO Space : Stage 2");

    public FrameStage2(){
        // frame.add(new Stage2());
        // frame.setSize(840, 680);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        // frame.setVisible(true);
    }

    @Override
    public void Stage_1() {
    }

    @Override
    public void Stage_2() {
        frame.add(new Stage2());
        frame.setSize(840, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void Stage_3() {
    }
}
