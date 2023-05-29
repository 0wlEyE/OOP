import java.awt.event.*;

public abstract class MyWindow implements WindowListener {
    public void updateBar() {
        Success.setWant(" ");
        Success.setValue(0);
        Success.setPrice(0);
        Success.update();
    }
    public void updateGoal(GoalFrame frame){
        frame.name.setText("");
        frame.money.setText(String.valueOf(0.0));
    }
    @Override
    public abstract void windowOpened(WindowEvent e);
    @Override
    public abstract void windowClosing(WindowEvent e);
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
