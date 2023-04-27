import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class SpaceInvader extends JFrame implements Commons{

 	JButton Level1;
	Image backGround;
	JPanel p;

	JFrame Ingame = new JFrame("GAO Space");
	JFrame MainFrame = new JFrame();
	
	public SpaceInvader() {
		// JLabel lab = new JLabel();
		p = new JPanel();
		ImageIcon ii = new ImageIcon("Background.png");
		backGround = ii.getImage();	
		// lab.setBounds(0, 0, 500, 500);
		// lab.setIcon(ii);

		Level1 = new JButton("Start");
		Level1.addActionListener(new ButtonListener());

		MainFrame.setTitle("Menu");

		p.add(Level1);

		MainFrame.add(p);
		// MainFrame.add(backGround);
		MainFrame.setSize(500, 500);
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
