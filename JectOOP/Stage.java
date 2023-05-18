import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Stage extends JPanel implements Runnable, DefaultCode {

	private ArrayList<Object> aliens;
	private ArrayList<Object> tankers;

	private int alienrow;
	private int tankerrow;
	private int enemyCol;
	private int numStage;
	private JFrame fr;

	private Player player;
	private Bullet shot;
	private boolean ragemode = false;
	private Bullet shotExtra;
	
	private GameOver gameend;
	private BackGround background;

	private JProgressBar bar;

	private int alienX = 150;
	private int alienY = 25;

	private int tankerx = 150;
	private int tankery;

	private int direction = -1;
	private int deaths = 0;


	private boolean ingame = true;
	private boolean havewon = true;

	private final String alienpix = "img/Alien.png";
	private final String tnkpic = "img/Tanker.png";

	private Thread animator;
	Sound sound = new Sound();

	// Default constructor
	public Stage(){
		this(0, 0, 0, 0, null);
	}

	public Stage(int numstage, int alienrow, int tankerrow, int enemyCol, JFrame fr) {	

		this.numStage = numstage;
		this.alienrow = alienrow;
		this.tankerrow = tankerrow;
		this.enemyCol = enemyCol;
		this.fr = fr;

		tankery = (ALIEN_HEIGHT + 25) * alienrow;
        
		addKeyListener(new TAdapter());
		setFocusable(true);
		gameInit();
		progressbar();
	}

	// Game Init
	public void gameInit() {

		aliens = new ArrayList<Object>();

		ImageIcon ii = new ImageIcon(this.getClass().getResource(alienpix));

		for (int i = 0; i < alienrow; i++) {
			for (int j = 0; j < enemyCol; j++) {
				Alien alien = new Alien(alienX + 50 * j, alienY + 50 * i);
				alien.setImage(ii.getImage());
				aliens.add(alien);
			}
		}

		tankers = new ArrayList<Object>();

		ImageIcon II = new ImageIcon(this.getClass().getResource(tnkpic));

		for (int i = 0; i < tankerrow; i++){
			for (int j = 0; j < enemyCol; j++){
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

	// Drawing section
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
		g.drawImage(background.getImage(), 17, 0, this);
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
    

	// Create gameover frame
	public void gameOver() {
		Graphics g = this.getGraphics();


		g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
		if (havewon == true ) {
			fr.dispose();
			new FrameWonGame(numStage);
		} else {
			fr.dispose();
			new FrameGameOver(numStage);
		}
		
	}

	// Animation cycle
	public void animationCycle() {
		boolean cond1 = numStage == 1 && deaths == NUMBER_OF_ALIENS_TO_DESTROY1;
		boolean cond2 = numStage == 2 && deaths == NUMBER_OF_ALIENS_TO_DESTROY2;
		boolean cond3 = numStage == 3 && deaths == NUMBER_OF_ALIENS_TO_DESTROY3;

		if (cond1 || cond2 || cond3) {
			ingame = false;
			System.out.println("Congratulation");
		}

		if (deaths == 10){
			sound.stop();
			sound.playSound(6);
			sound.playMusic(2);
		}
		

		// Player - action
        
		player.act();

		// Player - shot
		if (shot.isVisible()) {
			Iterator<Object> it = aliens.iterator();
			Iterator<Object> tk = tankers.iterator();
			int shotX = shot.getX();
			int shotY = shot.getY();

			// hit alien?
			while (it.hasNext()) {
				Alien alien = (Alien) it.next();
				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shot.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
							&& shotY >= (alienY)
							&& shotY <= (alienY + ALIEN_HEIGHT)) {
						deaths += alien.gotShot();
						bar.setValue(deaths);
						System.out.println(deaths);
						shot.die();
						
						if (bar.getValue() == 10){
							bar.setString("Fever!!");
							ragemode = true;
						}
					}
				}
			}
			
			
			// hit tanker?
			while (tk.hasNext()) {
				AlienTanker tan = (AlienTanker) tk.next();
				int tankerx = tan.getX();
				int tankery = tan.getY();

				if (tan.isVisible() && shot.isVisible()) {
					if (shotX >= (tankerx) && shotX <= (tankerx + TANKER_WIDTH)
							&& shotY >= (tankery)
							&& shotY <= (tankery + TANKER_WIDTH)) {
						
						// player shot tank
						int result = tan.setHP(tan.getHP()-1);
						shot.die();
						// System.out.println("hit");

						// tank dead
						if (result == 1){
							++deaths;
							bar.setValue(deaths);
							System.out.println(deaths);
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

		// Player - shot - rage mode
		if (shotExtra.isVisible()) {
			Iterator<Object> it = aliens.iterator();
			Iterator<Object> tk = tankers.iterator();

			int shotX = shotExtra.getX();
			int shotY = shotExtra.getY();

			// hit alien?
			while (it.hasNext()) {
				Alien alien = (Alien) it.next();
				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shotExtra.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
					&& shotY >= (alienY)
					&& shotY <= (alienY + ALIEN_HEIGHT)) {
						deaths += alien.gotShot();
						bar.setValue(deaths);
						shotExtra.die();
						System.out.println(deaths);

						if (bar.getValue() == 10){
							bar.setString("Fever!!");
						}
					}
				}
			}

			// hit tanker?
			while (tk.hasNext()) {
				AlienTanker tan = (AlienTanker) tk.next();
				int tankerx = tan.getX();
				int tankery = tan.getY();

				if (tan.isVisible() && shotExtra.isVisible()) {
					if (shotX >= (tankerx) && shotX <= (tankerx + TANKER_WIDTH)
							&& shotY >= (tankery)
							&& shotY <= (tankery + TANKER_WIDTH)) {
						
						// player shot tank
						int result = tan.setHP(tan.getHP()-1);
						shot.die();	
						shotExtra.die();
						// System.out.println("hit");
						
						// tank dead
						if (result == 1){
							// System.out.println("die");
							++deaths;
							bar.setValue(deaths);
							System.out.println(deaths);
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


		// Aliens
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
					System.out.println("Aliens invaded a galaxy!");
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
					System.out.println("Aliens invaded a galaxy!");
				}

				tank.act(direction);
			}
		}


		// alien - bombs
		Iterator<Object> i3 = aliens.iterator();
		Random generator = new Random();

		while (i3.hasNext()) {
			int shot = generator.nextInt(DefaultSpSHO / numStage);
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
				if (bombX >= (playerX - (PLAYER_WIDTH / 2)) && bombX <= (playerX + PLAYER_WIDTH) && bombY >= (playerY - 20)) {
					// player.destroyed();
					b.setDestroyed(true);
				}
			}

			if (numStage == 1 && !b.isDestroyed()) {
				b.setY(b.getY() + 5);
				if (b.getY() >= GROUND - BOMB_HEIGHT) {
					b.setDestroyed(true);
				}
			}
			if (numStage == 2 && !b.isDestroyed()) {
				b.setY(b.getY() + 4);
				if (b.getY() >= GROUND - BOMB_HEIGHT) {
					b.setDestroyed(true);
				}
			}
			if (numStage == 3 && !b.isDestroyed()) {
				b.setY(b.getY() + 2);
				if (b.getY() >= GROUND - BOMB_HEIGHT) {
					b.setDestroyed(true);
				}
			}
		}
	}

	// progress bar - rage mode
	public void progressbar(){
		this.setLayout(new BorderLayout());
		bar = new JProgressBar(JProgressBar.VERTICAL, 0, 10);
		bar.setStringPainted(true);
		bar.setForeground(Color.white);
		bar.setBackground(Color.black);
		bar.setBorderPainted(false);
		this.add(bar, BorderLayout.WEST);
	}

	// run game
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

	// Listener
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
					if (!shot.isVisible() && !ragemode){
						sound.playSound(3);
						shot = new Bullet(x, y);
					}else if(ragemode && !shotExtra.isVisible()){
						sound.playSound(3);
						sound.playSound(3);
						shot = new Bullet(x, y);
						shotExtra = new Bullet(x, shot.getY() + 100);
						// System.out.println("double!");
					}
				}
			}
		}
	}
}
