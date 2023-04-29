import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SpaceInvader extends JPanel implements Commons{

 	JButton Level1;
	Image backGround;
	JFrame Ingame = new JFrame("GAO Space");
	JFrame MainFrame = new JFrame();
	
	public SpaceInvader() {
		ImageIcon bg = new ImageIcon("bgmain.png");
		backGround = bg.getImage();	

		Level1 = new JButton("Start");
		Level1.addActionListener(new ButtonListener());

		MainFrame.setTitle("Menu");

		add(Level1);

		MainFrame.add(this);
		// MainFrame.add(backGround);
		MainFrame.setSize(840, 680);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setVisible(true);
		MainFrame.setResizable(false);

	}

	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(backGround, 0, 0, null);
	}

	public void closeIntro() {
		MainFrame.dispose();
	}

	public static void main(String[] args) {
		new SpaceInvader();
	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			Ingame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Ingame.setSize(BOARD_WIDTH, BOARD_HEIGTH);
			Ingame.getContentPane().add(new Board());
			Ingame.setResizable(false);
			Ingame.setLocationRelativeTo(null);
			Ingame.setVisible(true);
			closeIntro();

		}
	}

}
