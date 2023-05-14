import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;

public class Stage1 extends JPanel implements Runnable, DefaultCode {

	private ArrayList<Object> aliens;
	private ArrayList<Object> tankers;
	private Player player;
	private Bullet shot;

	private boolean ragemode = false;
	private Bullet shotExtra;
	
	private GameOver gameend;
	private WonGame vunnet;
	private BackGround background;

	private JProgressBar bar;

	private int alienX = 150;
	private int alienY = 25;

	private int tankerx = 150;
	private int tankery = 230;

	private int direction = -1;
	private int deaths = 0;
	private int cntshot = 0;

	private boolean ingame = true;
	private boolean havewon = true;
	private final String expl = "img/explosion.png";
	private final String alienpix = "img/Alien.png";
	private final String tnkpic = "img/Tanker.png";
	private String message = "You Died";

	private Thread animator;

	public Stage1() {	
        
		addKeyListener(new TAdapter());
		setFocusable(true);
		gameInit();
		progressbar();
	}


	public void gameInit() {
		aliens = new ArrayList<Object>();

		ImageIcon ii = new ImageIcon(this.getClass().getResource(alienpix));

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				Alien alien = new Alien(alienX + 50 * j, alienY + 50 * i);
				alien.setImage(ii.getImage());
				aliens.add(alien);
			}
		}

		tankers = new ArrayList<Object>();

		ImageIcon II = new ImageIcon(this.getClass().getResource(tnkpic));

		for (int i = 0; i < 1; i++){
			for (int j = 0; j < 6; j++){
				AlienTanker tank = new AlienTanker(tankerx + 50 * j, tankery + 50 * i);
				tank.setImage(II.getImage());
				tankers.add(tank);
			}
		}

		player = new Player();
		shot = new Bullet();
		shotExtra = new Bullet();
		shotExtra.setVisible(false);

		if (ingame) {
			animator = new Thread(this);
			animator.start();
		}
	}

	public void drawAliens(Graphics g) {
		Iterator<Object> it = aliens.iterator();

		while (it.hasNext()) {
			Alien alien = (Alien) it.next();

			if (alien.isVisible()) {
				g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
			}

			if (alien.isDying()) {
				alien.die();
			}
		}
	}

	public void drawTanker(Graphics g) {
		Iterator<Object> tk = tankers.iterator();

		while (tk.hasNext()) {
			AlienTanker tan = (AlienTanker) tk.next();

			if (tan.isVisible()) {
				g.drawImage(tan.getImage(), tan.getX(), tan.getY(), this);
			}

			if (tan.isDying()) {
				tan.die();;
			}
		}
	}

	public void drawPlayer(Graphics g) {
		if (player.isVisible()) {
			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}

		if (player.isDying()) {
			player.die();
			havewon = false;
			ingame = false;
		}
	}

	public void drawGameEnd(Graphics g) {
		g.drawImage(gameend.getImage(), 0, 0, this);
	}

	public void drawBackGround(Graphics g){
		g.drawImage(background.getImage(), 13, 0, this);
	}

	public void drawShot(Graphics g) {
		if (shot.isVisible())
			g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
	}

	public void drawShotExtra(Graphics g) {
		if (shotExtra.isVisible())
			g.drawImage(shotExtra.getImage(), shotExtra.getX(), shotExtra.getY(), this);
	}

	public void drawBombing(Graphics g) {
		Iterator<Object> i3 = aliens.iterator();

		while (i3.hasNext()) {
			Alien a = (Alien) i3.next();

			Bomb b = a.getBomb();

			if (!b.isDestroyed()) {
				g.drawImage(b.getImage(), b.getX(), b.getY(), this);
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		background = new BackGround();

		if (ingame) {

			drawBackGround(g);
			drawAliens(g);
			drawPlayer(g);
			drawShot(g);
			drawBombing(g);
			drawTanker(g);
			drawShotExtra(g);
			
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
    }

	public void gameOver() {
		Graphics g = this.getGraphics();

		gameend = new GameOver();
		vunnet = new WonGame();

		// g.setColor(Color.black);
		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
		if (havewon == true) {
			g.drawImage(vunnet.getImage(), 0, 0, this);
		} else {
			g.drawImage(gameend.getImage(), 0, 0, this);
		}
		g.setColor(new Color(0, 32, 48));
		g.fillRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);
		g.setColor(Color.white);
		g.drawRect(50, BOARD_WIDTH / 2 - 30, BOARD_WIDTH - 100, 50);

		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(message, (BOARD_WIDTH - metr.stringWidth(message)) / 2,
				BOARD_WIDTH / 2);
	}

	public void animationCycle() {
		if (deaths == NUMBER_OF_ALIENS_TO_DESTROY1) {
			ingame = false;
			message = "Congratulation";
		}

		// player
        
		player.act();

		// shot
		if (shot.isVisible()) {
			Iterator<Object> it = aliens.iterator();
			Iterator<Object> tk = tankers.iterator();
			int shotX = shot.getX();
			int shotY = shot.getY();

			while (it.hasNext()) {
				Alien alien = (Alien) it.next();
				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shot.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
							&& shotY >= (alienY)
							&& shotY <= (alienY + ALIEN_HEIGHT)) {
						ImageIcon ii = new ImageIcon(getClass().getResource(expl));
						alien.setImage(ii.getImage());
						alien.setDying(true);
						deaths++;
						shot.die();
						bar.setValue(deaths);
						if (bar.getValue() == 10){
							bar.setString("Fever!!");
							ragemode = true;
						}
					}
				}
			}

			while (tk.hasNext()) {
				AlienTanker tan = (AlienTanker) tk.next();
				int tankerx = tan.getX();
				int tankery = tan.getY();

				if (tan.isVisible() && shot.isVisible()) {
					if (shotX >= (tankerx) && shotX <= (tankerx + TANKER_WIDTH)
							&& shotY >= (tankery)
							&& shotY <= (tankery + TANKER_WIDTH)) {
						ImageIcon II = new ImageIcon(getClass().getResource(expl));
						cntshot++;
						shot.die();	
						System.out.println("hit");

						if (cntshot == TANKER_HITPOINT){
							System.out.println("die");
							tan.setImage(II.getImage());
							tan.setDying(true);
							deaths++;
							shot.die();	
							cntshot = 0;
							bar.setValue(deaths);
							if (bar.getValue() == 10){
								bar.setString("Fever!!");
								ragemode = true;
							}
						}
					}
						
				}
			}

			int y = shot.getY();
			y -= 8;
			if (y < 0)
				shot.die();
			else
			shot.setY(y);
		}

		//shot - rage mode
		if (shotExtra.isVisible()) {
			Iterator<Object> it = aliens.iterator();
			Iterator<Object> tk = tankers.iterator();

			int shotX = shotExtra.getX();
			int shotY = shotExtra.getY();



			while (it.hasNext()) {
				Alien alien = (Alien) it.next();
				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shotExtra.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
							&& shotY >= (alienY)
							&& shotY <= (alienY + ALIEN_HEIGHT)) {
						ImageIcon ii = new ImageIcon(getClass().getResource(expl));
						alien.setImage(ii.getImage());
						alien.setDying(true);
						deaths++;
						shotExtra.die();
						bar.setValue(deaths);
						if (bar.getValue() == 10){
							bar.setString("Fever!!");
						}
					}
				}
			}

			while (tk.hasNext()) {
				AlienTanker tan = (AlienTanker) tk.next();
				int tankerx = tan.getX();
				int tankery = tan.getY();

				if (tan.isVisible() && shotExtra.isVisible()) {
					if (shotX >= (tankerx) && shotX <= (tankerx + TANKER_WIDTH)
							&& shotY >= (tankery)
							&& shotY <= (tankery + TANKER_WIDTH)) {
						ImageIcon II = new ImageIcon(getClass().getResource(expl));
						cntshot++;
						shotExtra.die();	
						System.out.println("hit");

						if (cntshot == TANKER_HITPOINT){
							System.out.println("die");
							tan.setImage(II.getImage());
							tan.setDying(true);
							deaths++;
							shotExtra.die();	
							cntshot = 0;
							bar.setValue(deaths);
							if (bar.getValue() == 10){
								bar.setString("Fever!!");
							}
						}
					}
						
				}
			}

			int y = shotExtra.getY();
			y -= 8;
			if (y < 0)
				shotExtra.die();
			else
				shotExtra.setY(y);
		}

		// aliens

		Iterator<Object> it1 = aliens.iterator();

		while (it1.hasNext()) {
			Alien a1 = (Alien) it1.next();
			int x = a1.getX();

			if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
				direction = -1;
				Iterator<Object> i1 = aliens.iterator();
				while (i1.hasNext()) {
					Alien a2 = (Alien) i1.next();
					a2.setY(a2.getY() + GO_DOWN);
				}

				Iterator<Object> tk1 = tankers.iterator();

				while (tk1.hasNext()) {
					AlienTanker tan = (AlienTanker) tk1.next();
					tan.setY(tan.getY() + GO_DOWN);
				}
			}

			if (x <= BORDER_LEFT && direction != 1) {
				direction = 1;

				Iterator<Object> i2 = aliens.iterator();
				while (i2.hasNext()) {
					Alien a = (Alien) i2.next();
					a.setY(a.getY() + GO_DOWN);
				}

				Iterator<Object> tk1 = tankers.iterator();

				while (tk1.hasNext()) {
					AlienTanker tan = (AlienTanker) tk1.next();
					tan.setY(tan.getY() + GO_DOWN);
				}
			}
		}

		Iterator<Object> it = aliens.iterator();

		while (it.hasNext()) {
			Alien alien = (Alien) it.next();
			if (alien.isVisible()) {

				int y = alien.getY();

				if (y > GROUND - ALIEN_HEIGHT) {
					havewon = false;
					ingame = false;
					message = "Aliens invaded a galaxy!";
				}

				alien.act(direction);
			}
		}

		// Tankers
		
		Iterator<Object> tk = tankers.iterator();
		
		while (tk.hasNext()) {
			AlienTanker t1 = (AlienTanker)tk.next();
			int x = t1.getX();

			if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1){
				direction = -1;
			}

			if (x <= BORDER_LEFT && direction != 1) {
				direction = 1;
			}

		}

		Iterator<Object> tan = tankers.iterator();

		while (tan.hasNext()) {
			AlienTanker tank = (AlienTanker) tan.next();
			if (tank.isVisible()) {

				int y = tank.getY();

				if (y > GROUND - TANKER_HEIGHT) {
					havewon = false;
					ingame = false;
					message = "Aliens invaded a galaxy!";
				}

				tank.act(direction);
			}
		}


		// bombs

		Iterator<Object> i3 = aliens.iterator();
		Random generator = new Random();

		while (i3.hasNext()) {
			int shot = generator.nextInt(200);
			Alien a = (Alien) i3.next();
			Bomb b = a.getBomb();
			if (shot == CHANCE && a.isVisible() && b.isDestroyed()) {

				b.setDestroyed(false);
				b.setX(a.getX());
				b.setY(a.getY());
			}

			int bombX = b.getX();
			int bombY = b.getY();
			int playerX = player.getX();
			int playerY = player.getY();

			if (player.isVisible() && !b.isDestroyed()) {
				if (bombX >= (playerX) && bombX <= (playerX + PLAYER_WIDTH) && bombY >= (playerY) && bombY <= (playerY + PLAYER_HEIGHT)) {
					ImageIcon ii = new ImageIcon(this.getClass().getResource(expl));
					player.setImage(ii.getImage());
					player.setDying(true);
					b.setDestroyed(true);
				}
			}

			if (!b.isDestroyed()) {
				b.setY(b.getY() + 1);
				if (b.getY() >= GROUND - BOMB_HEIGHT) {
					b.setDestroyed(true);
				}
			}
		}
	}

	public void progressbar(){
		this.setLayout(new BorderLayout());
		bar = new JProgressBar(JProgressBar.VERTICAL, 0, 10);
		bar.setStringPainted(true);
		bar.setForeground(Color.white);
		bar.setBackground(Color.black);
		bar.setBorderPainted(false);
		this.add(bar, BorderLayout.WEST);
	}

	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (ingame) {
			repaint();
			animationCycle();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0)
				sleep = 1;
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {}
			beforeTime = System.currentTimeMillis();
		}
		gameOver();
	}

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			if (ingame) {
				player.keyPressed(e);
				
				int x = player.getX();
				int y = player.getY();
				
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_SPACE){
					if (!shot.isVisible()){
						shot = new Bullet(x, y);
					}else if(ragemode && !shotExtra.isVisible()){
						shotExtra = new Bullet(x, y);
						System.out.println("double!");
					}
				}
			}
		}
	}
}