import java.awt.event.*;

public abstract class RubJaiWindow implements WindowListener{
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
