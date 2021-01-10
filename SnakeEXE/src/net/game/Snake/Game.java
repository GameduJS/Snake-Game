package net.game.Snake;

/* IMPORTS */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import net.game.config.EncodedConfigManager;
import net.game.functions.ComponentDisposer;
import net.game.functions.DrawUtils;
import net.game.functions.GameFrame;
import net.game.functions.MenuFrame;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5725813697671548715L;
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 600;
	public static final int UNIT_SIZE = 25;
	private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	public static int SPAWNRATE = 5;
	private static int DELAY = 150;
	
	public List<Component> pausedScreenComponents = new ArrayList<>();
	public List<Component> endScreenComponents = new ArrayList<>();
	
	private static final int x[] = new int[GAME_UNITS];
	private static final int y[] = new int[GAME_UNITS];
	
	public int bodyParts = 2;
	public int appleEaten = 1;
	private int appleX;
	private int appleY;
	
	public static final int SCREEN_WIDTH_HALF = SCREEN_WIDTH / 2;
	public static final int SCREEN_HEIGHT_HALF = SCREEN_HEIGHT / 2;
	
	private char direction = 'R';
	
	private boolean running = false;
	private boolean paused = false;
	private boolean goldenApple = false;
	private boolean ended = false;
	
	private JButton continueButton;
	private JButton menuButton;
	private JButton newGameButton;
	
	private Timer timer;
	private Random random;
	private EncodedConfigManager manager;
	private SimpleDateFormat date;
	
	Font font;
	File score;
	ImageIcon icon;

	public Game() {
		
		
		
		date = new SimpleDateFormat("[HH:mm:ss:SS]");

		System.out.println("[SNAKE]" + date.format(new Date()) + " << Game created...");
		System.out.println("[SNAKE]" + date.format(new Date()) + " << Ready to play");
		
		icon = new ImageIcon(new ImageIcon(this.getClass().getResource("/button.png")).getImage());
		font = new Font("Minecraft", Font.BOLD, 20);
		
		
		continueButton = new JButton("CONTINUE");
		continueButton.setBackground(Color.LIGHT_GRAY);
		continueButton.setFont(font);
		continueButton.setBounds(SCREEN_WIDTH_HALF - 225, SCREEN_HEIGHT_HALF + 145, 450, 30);
		continueButton.addActionListener(this);
		
		menuButton = new JButton("BACK TO MENU");
		menuButton.setBackground(Color.LIGHT_GRAY);
		menuButton.setFont(font);
		menuButton.setBounds(SCREEN_WIDTH_HALF - 225, SCREEN_HEIGHT_HALF + 185, 450, 30);
		menuButton.addActionListener(this);
		
		newGameButton = new JButton("NEW GAME");
		newGameButton.setBackground(Color.LIGHT_GRAY);
		newGameButton.setFont(font);
		newGameButton.setBounds(SCREEN_WIDTH_HALF - 225, SCREEN_HEIGHT_HALF + 145, 450, 30);
		newGameButton.addActionListener(this);
		
		
		pausedScreenComponents.add(continueButton);
		pausedScreenComponents.add(menuButton);
		endScreenComponents.add(newGameButton);
		endScreenComponents.add(menuButton);
		
		
		manager = SnakeController.c;
		score = SnakeController.getScoreFile();
		random = new Random();
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		this.setBackground(Color.BLACK);
		
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {

		DrawUtils.setGraphics(g);
		
//		Graphics2D g2d = (Graphics2D) g;
//		RoundRectangle2D r = new RoundRectangle2D.Double(0, 0, SCRENN_WIDTH, SCRENN_HEIGHT, SCRENN_WIDTH, SCRENN_HEIGHT);
//		g2d.draw(r);

		if(running) {
			
			/* Remove end screen buttons from screen */
			if(ended) {
				endScreenComponents.forEach(c -> {
					this.remove(c);
				});
				ended = false;
			}
			
			/* Add's paused screen buttons to screen */
			if(paused) {
				pausedScreenComponents.forEach(c -> { 
					this.add(c); 
				});
			} else {
				try {
					for(Component c : pausedScreenComponents) {
						this.remove(c);
					}
				} catch (Exception e) {
					System.out.println("Cannot remove Components " + e.getCause());
				}
			}
			
			
			if(goldenApple) 
				DrawUtils.drawGoldenApple(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			 else 
				DrawUtils.drawApple(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			
			
			
			
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					DrawUtils.drawBodyPart(Color.CYAN, x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					DrawUtils.drawBodyPart(Color.GREEN, x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			
			g.setColor(new Color(255, 20, 80));
			g.setFont(new Font("Minecraft", Font.ITALIC, 42));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + appleEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + appleEaten)) / 2, g.getFont().getSize());
			
			
		} else gameOver(g);

		
	}
	
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
		
		int spawn = random.nextInt(100) + 1;
		
		if(spawn <= SPAWNRATE) {
			goldenApple = true;
		}
	}
	
	public void move() {
		for(int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}
		
		switch(direction) {
		
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':	
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
		
	}
	
	public void checkApple() {
		
		if((x[0] == appleX) && (y[0] == appleY)) {
			if(!goldenApple) {
				bodyParts++;
				appleEaten++;
				System.out.println("[SNAKE]" + date.format(new Date()) + " << Player has eaten an apple");
			} else {
				bodyParts+=1;
				appleEaten = appleEaten + 4;
				goldenApple = false;
				System.out.println("[SNAKE]" + date.format(new Date()) + " << Player has eaten an golden apple");
			}
			
			updateDelay();
			newApple();
		}
		
	}
	
	private void updateDelay() {
		
		switch (appleEaten) {
			case 10: 
				DELAY = DELAY - 10;
				break;
			case 20: 
				DELAY = DELAY - 10;
				break;
			case 30: 
				DELAY = DELAY - 10;
				break;
			case 50: 
				DELAY = DELAY - 10;
				break;
			case 70: 
				DELAY = DELAY - 10;
				break;
			case 90: 
				DELAY = DELAY - 10;
				break;
			case 100: 
				DELAY = DELAY - 10;
				break;
		}
		
		timer.setDelay(DELAY);
		
	}

	public void checkCollisions() {
		
		if(running == false) {
			timer.stop();
		}
		
		//checks if head collide with boy
		for(int i = bodyParts; i > 0; i--) {
			
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
			
		}
		
		
		//Check if head touches left border
		if(x[0] < 0) {
			running = false;
		}
		//Check if head touches right border
		if(x[0] >= SCREEN_WIDTH) {
			running = false;
		}
		//Check if head touches top border
		if(y[0] < 0) {
			running = false;
		}
		//Check if head touches bottom border
		if(y[0] >= SCREEN_HEIGHT) {
			running = false;
		}
		
	}
	
	public void gameOver(Graphics g) {
		
		
		g.setColor(Color.RED);
		g.setFont(new Font("Minecraft", Font.ITALIC, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game over", (SCREEN_WIDTH - metrics.stringWidth("Game over")) / 2, SCREEN_WIDTH / 2);
		
		
		g.setColor(Color.RED);
		g.setFont(new Font("Minecraft", Font.ITALIC, 42));
		FontMetrics metricsApple = getFontMetrics(g.getFont());
		g.drawString("Score: " + appleEaten, (SCREEN_WIDTH - metricsApple.stringWidth("Score: " + appleEaten)) / 2, g.getFont().getSize());	
		
		ended = true;
		endScreenComponents.forEach(c -> {
			this.add(c);
		});
		
		try {
			
			if(Integer.valueOf(manager.get("score")) < appleEaten) {
				manager.set("score", String.valueOf(appleEaten));
			}
			
			manager.saveCurrencyFile();
			System.out.println("[SNAKE]" + date.format(new Date()) + " << Shutdown...");
			timer.stop();
		
		} catch (Exception e) { }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Fragt ab ob die Quelle ein Button ist um Daten zu sparen
		if(e.getSource() instanceof JButton) {
			
			if(e.getSource().equals(continueButton)) {
				paused = false;
			}
			
			if(e.getSource().equals(menuButton)) {
				
				save();
				reset();
				
				new ComponentDisposer((JPanel)this);
				
				try {
					new MenuFrame();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if(e.getSource().equals(newGameButton)) {
							
					save();
					new ComponentDisposer((JPanel)this);
					reset();
					new GameFrame();
				
			}
		}
		
		// Wenn das Game läuft und nicht pausiert ist die methoden aufrufen
		if(running && !paused) { 
			move();
			checkApple();
			checkCollisions();
			
		}
		repaint();	
	}	
	
	private void reset() {

		for(int i = 0; i < bodyParts; i++) {
			x[i] = 0;
			y[i] = 0;
		}
		DELAY = 150;
		this.bodyParts = 2;
		this.appleEaten = 1;
		appleX = 0;
		appleY = 0;
		direction = 'R';
		running = false;
		paused = false;
		goldenApple = false;
		ended = false;
	}
	
	private void save() {
		
		
		
	}
	
	
	
	

	
	
	public class MyKeyAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			
			
			switch(e.getKeyCode()) {
			
			case KeyEvent.VK_LEFT: 
				if(direction != 'R' && !paused) { direction = 'L'; }
				break;
			case KeyEvent.VK_RIGHT: 
				if(direction != 'L' && !paused) { direction = 'R'; }
				break;
			case KeyEvent.VK_UP: 
				if(direction != 'D' && !paused) { direction = 'U'; }
				break;
			case KeyEvent.VK_DOWN: 
				if(direction != 'U' && !paused) { direction = 'D'; }
				break;
			case KeyEvent.VK_A: 
				if(direction != 'R' && !paused) { direction = 'L'; }
				break;
			case KeyEvent.VK_D: 
				if(direction != 'L' && !paused) { direction = 'R'; }
				break;
			case KeyEvent.VK_W: 
				if(direction != 'D' && !paused) { direction = 'U'; }
				break;
			case KeyEvent.VK_S: 
				if(direction != 'U' && !paused) { direction = 'D'; }
				break;
			case KeyEvent.VK_ESCAPE:
				paused = !paused;
				break;
			}
		}
	}
}