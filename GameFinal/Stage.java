import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Stage extends JPanel implements Runnable, DefaultCode, KeyListener{

	private ArrayList<Object> aliens;
	private ArrayList<Object> tankers;

	private int alienrow;
	private int tankerrow;
	private int enemyCol;
	private int numStage;
	private JFrame fr;

	private Player player;
	private Bullet shot;
	private Bullet shotExtra;
	private boolean ragemode = false;
	
	private BackGround background;

	private JProgressBar bar;

	private int alienX = 150;
	private int alienY = 25;

	private int tankerX = 150;
	private int tankerY;

	private int direction = -1;
	private int deaths = 0;

	private boolean ingame = true;
	private boolean havewon = true;
	private boolean ping = false;

	private final String alienPic = "img/Alien.png";
	private final String tankPic = "img/Alien_Shield.png";

	private Thread animator;
	private Sound sound = new Sound();
	private Sound music = new Sound();

	// Constructure
	public Stage(){
		this(0, 0, 0, 0, null);
	}

	public Stage(int numstage, int alienrow, int tankerrow, int enemyCol, JFrame fr) {	

		this.numStage = numstage;
		this.alienrow = alienrow;
		this.tankerrow = tankerrow;
		this.enemyCol = enemyCol;
		this.fr = fr;

		tankerY = (ALIEN_HEIGHT + 25) * alienrow;
		music.playMusic(1);
        
		addKeyListener(this);
		setFocusable(true);
		gameInit();
		progressbar();

	}

	public void gameInit() {

		aliens = new ArrayList<Object>();

		ImageIcon img = new ImageIcon(this.getClass().getResource(alienPic));

		for (int i = 0; i < alienrow; i++) {
			for (int j = 0; j < enemyCol; j++) {
				Alien alien = new Alien(alienX + 50 * j, alienY + 50 * i);
				alien.setImage(img.getImage());
				aliens.add(alien);
			}
		}

		tankers = new ArrayList<Object>();

		ImageIcon Img = new ImageIcon(this.getClass().getResource(tankPic));

		for (int i = 0; i < tankerrow; i++){
			for (int j = 0; j < enemyCol; j++){
				AlienTanker tank = new AlienTanker(tankerX + 50 * j, tankerY + 50 * i);
				tank.setImage(Img.getImage());
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
		Iterator<Object> it_aliens = aliens.iterator();

		while (it_aliens.hasNext()) {
			Alien alien = (Alien) it_aliens.next();

			if (alien.isVisible()) {
				g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
			}

			if (alien.isDying()) {
				alien.die();
			}
		}
	}

	public void drawTanker(Graphics g) {
		Iterator<Object> it_tanks = tankers.iterator();

		while (it_tanks.hasNext()) {
			AlienTanker tank = (AlienTanker) it_tanks.next();

			if (tank.isVisible()) {
				g.drawImage(tank.getImage(), tank.getX(), tank.getY(), this);
			}

			if (tank.isDying()) {
				tank.die();;
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
		Iterator<Object> it_bomb = aliens.iterator();

		while (it_bomb.hasNext()) {
			Alien alien = (Alien) it_bomb.next();

			Bomb bomb = alien.getBomb();

			if (!bomb.isDestroyed()) {
				g.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
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
	}
    
	// Create gameover frame
	public void gameOver() {
		
		if (havewon == true ) {
			fr.dispose();
			music.stop();
			new FrameWonGame(numStage);
		} else {
			fr.dispose();
			music.stop();
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
			sound.stop();
		}

		// Player - action
		if (ping){
			sound.playSound(6);
			ping = false;
			music.stop();
			music.playMusic(2);
		}	

		player.act();

		// Player - shot
		if (shot.isVisible()) {
			Iterator<Object> it_alien = aliens.iterator();
			Iterator<Object> it_tank = tankers.iterator();
			int shotX = shot.getX();
			int shotY = shot.getY();

			// hit alien?
			while (it_alien.hasNext()) {
				Alien alien = (Alien) it_alien.next();
				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shot.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
						&& shotY >= (alienY) && shotY <= (alienY + ALIEN_HEIGHT)) {

						deaths += alien.gotShot();
						bar.setValue(deaths);
						shot.die();
						
						if (deaths == 10){
							bar.setString("Fever!!");							
							ping = true;
							ragemode = true;	
							bar.setForeground(Color.GREEN);
						}
					}
				}
			}
			
			// hit tanker?
			while (it_tank.hasNext()) {
				AlienTanker tank = (AlienTanker) it_tank.next();
				int tankerX = tank.getX();
				int tankerY = tank.getY();

				if (tank.isVisible() && shot.isVisible()) {
					if (shotX >= (tankerX) && shotX <= (tankerX + TANKER_WIDTH)
						&& shotY >= (tankerY) && shotY <= (tankerY + TANKER_WIDTH)) {
						
						// player shot tank
						int result = tank.setHP(tank.getHP()-1);
						shot.die();

						// tank dead
						if (result == 1){
							++deaths;
							bar.setValue(deaths);
							if (deaths == 10){								
								ping = true;									
								ragemode = true;
								bar.setForeground(Color.GREEN);
							}
						}
					}					
				}
			}
			//move bullet
			int y = shot.getY();
			y -= 8;
			if (y < 0)
				shot.die();
			else
			shot.setY(y);
		}

		// Player - shot - rage mode
		if (shotExtra.isVisible()) {
			Iterator<Object> it_alien = aliens.iterator();
			Iterator<Object> it_tank = tankers.iterator();

			int shotX = shotExtra.getX();
			int shotY = shotExtra.getY();

			// hit alien?
			while (it_alien.hasNext()) {
				Alien alien = (Alien) it_alien.next();
				int alienX = alien.getX();
				int alienY = alien.getY();

				if (alien.isVisible() && shotExtra.isVisible()) {
					if (shotX >= (alienX) && shotX <= (alienX + ALIEN_WIDTH)
						&& shotY >= (alienY) && shotY <= (alienY + ALIEN_HEIGHT)) {
						deaths += alien.gotShot();
						bar.setValue(deaths);
						shotExtra.die();

						if (deaths == 10){							
							ping = true;	
							bar.setForeground(Color.GREEN);						
						}
					}
				}
			}

			// hit tanker?
			while (it_tank.hasNext()) {
				AlienTanker tank = (AlienTanker) it_tank.next();
				int tankerX = tank.getX();
				int tankerY = tank.getY();

				if (tank.isVisible() && shotExtra.isVisible()) {
					if (shotX >= (tankerX) && shotX <= (tankerX + TANKER_WIDTH)
						&& shotY >= (tankerY) && shotY <= (tankerY + TANKER_WIDTH)) {
						
						// player shot tank
						int result = tank.setHP(tank.getHP()-1);
						shot.die();	
						shotExtra.die();
						
						// tank dead
						if (result == 1){
							++deaths;
							bar.setValue(deaths);

							if (deaths == 10){
								ping = true;
								bar.setForeground(Color.GREEN);
							}
						}
					} 		
				}
			}

			//move Extrabullet 
			int y = shotExtra.getY();
			y -= 8;
			if (y < 0)
				shotExtra.die();
			else
				shotExtra.setY(y);
		}

		// Aliens
		Iterator<Object> it_alien = aliens.iterator();

		while (it_alien.hasNext()) {
			Alien alien = (Alien) it_alien.next();
			int x = alien.getX();

			//move to left
			if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
				direction = -1;
				Iterator<Object> it_alienDown = aliens.iterator();
				//move downward
				while (it_alienDown.hasNext()) {
					Alien alien2 = (Alien) it_alienDown.next();
					alien2.setY(alien2.getY() + GO_DOWN);
				}

				Iterator<Object> it_tank = tankers.iterator();

				while (it_tank.hasNext()) {
					AlienTanker it_tankDown = (AlienTanker) it_tank.next();
					it_tankDown.setY(it_tankDown.getY() + GO_DOWN);
				}
			}

			//move to right
			if (x <= BORDER_LEFT && direction != 1) {
				direction = 1;

				Iterator<Object> it_alien2 = aliens.iterator();
				while (it_alien2.hasNext()) {
					Alien alien1 = (Alien) it_alien2.next();
					alien1.setY(alien1.getY() + GO_DOWN);
				}

				Iterator<Object> it_tank1 = tankers.iterator();

				while (it_tank1.hasNext()) {
					AlienTanker tank = (AlienTanker) it_tank1.next();
					tank.setY(tank.getY() + GO_DOWN);
				}
			}
		}

		//go through bottom border
		Iterator<Object> it_alienBottom = aliens.iterator();

		while (it_alienBottom.hasNext()) {
			Alien alien = (Alien) it_alienBottom.next();
			if (alien.isVisible()) {
				int y = alien.getY();
				if (y > GROUND - ALIEN_HEIGHT) {
					havewon = false;
					ingame = false;
				}
				//move alien left-right
				alien.act(direction);
			}
		}
		// Tankers
		Iterator<Object> it_tank = tankers.iterator();
		
		while (it_tank.hasNext()) {
			AlienTanker tank1 = (AlienTanker)it_tank.next();
			int x = tank1.getX();
			//move to left
			if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1){
				direction = -1;
			}
			//move to right
			if (x <= BORDER_LEFT && direction != 1) {
				direction = 1;
			}
		}

		Iterator<Object> it_tanker = tankers.iterator();

		while (it_tanker.hasNext()) {
			AlienTanker tank = (AlienTanker) it_tanker.next();
			if (tank.isVisible()) {

				int y = tank.getY();

				if (y > GROUND - TANKER_HEIGHT) {
					havewon = false;
					ingame = false;
				}
				//move tank left-right
				tank.act(direction);
			}
		}

		// alien - bombs
		Iterator<Object> it_bomb = aliens.iterator();
		Random generator = new Random();

		while (it_bomb.hasNext()) {
			int shot = generator.nextInt(DefaultSpSHO / numStage);
			Alien a = (Alien) it_bomb.next();
			Bomb b = a.getBomb();

			//alien drop bomb
			if (shot == CHANCE && a.isVisible() && b.isDestroyed()) {
				b.setDestroyed(false);
				b.setX(a.getX());
				b.setY(a.getY());
			}

			int bombX = b.getX();
			int bombY = b.getY();
			int playerX = player.getX();
			int playerY = player.getY();

			//player and bomb have not destroyed yet
			if (player.isVisible() && !b.isDestroyed()) {
				// Player collision
				if (bombX >= (playerX - (PLAYER_WIDTH / 2) + 5) && 
					bombX <= (playerX + PLAYER_WIDTH) && 
					bombY >= (playerY - 25)) {
					player.destroyed();
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
				b.setY(b.getY() + 3);
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

			if (sleep < 0){
				sleep = 1;
			}
		
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {}
			beforeTime = System.currentTimeMillis();
		}
		gameOver();
	}

	//KeyListener
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
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
					sound.playSound(7);
					shot = new Bullet(x, y);
					shotExtra = new Bullet(x, shot.getY() + 100);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
}
