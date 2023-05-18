
import javax.swing.*;

public class FrameStage extends StageSelect{

    JProgressBar bar;
    public JFrame frame;
    // private Sound sound;

    public FrameStage(){

        bar = new JProgressBar();
        frame = new JFrame("GAO Space");
        // sound = new Sound();

        frame.setSize(840, 680);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    

    @Override
    public void createStage(int num) {
        if (num == 1){
            // sound.playMusic(1);
            frame.add(new Stage(1, 3, 1, 6, frame));
        }
        if (num == 2){
            // sound.playMusic(1);
            frame.add(new Stage(2, 5, 1, 6, frame));
        }
        if (num == 3){
            // sound.playMusic(1);
            frame.add(new Stage(3, 3, 0, 10, frame));
        }
    }
}
