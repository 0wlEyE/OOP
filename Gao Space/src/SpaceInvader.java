import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class SpaceInvader extends JFrame implements Commons{

	private JButton Level1;

	JFrame Ingame = new JFrame("GAO Space");
	JFrame MainFrame = new JFrame();

	public SpaceInvader() {
		String topmessage = "Hello";
		String message = "GG";

		Level1 = new JButton("Start");
		Level1.addActionListener(new ButtonListener());
		// Level1.setBounds(800, 800, 200, 500);

		JLabel tekst = new JLabel(message, SwingConstants.CENTER);
		JLabel toptekst = new JLabel(topmessage, SwingConstants.CENTER);

		Font font = new Font("Helvetica", Font.BOLD, 32);
		tekst.setFont(font);

		Font font2 = new Font("Helvetica", Font.BOLD, 20);
		toptekst.setFont(font2);

		MainFrame.setTitle("Menu");

		MainFrame.add(tekst);

		MainFrame.add(toptekst, BorderLayout.PAGE_START);
		JPanel nedredel = new JPanel();
		nedredel.add(Level1);

		MainFrame.add(nedredel, BorderLayout.PAGE_END);
		MainFrame.setSize(500, 500);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setVisible(true);
		MainFrame.setResizable(false);

	}

	public void closeIntro() {
		MainFrame.dispose();
	}

	public static void main(String[] args) {
		new SpaceInvader();
	}

	private class ButtonListener implements ActionListener {

                @Override
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
