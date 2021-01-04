package net.game.functions;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.game.Snake.SnakeController;

@SuppressWarnings("serial")
public class MenuFrame extends JFrame implements ActionListener {
	
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 600;
	
	private static final int SCREEN_WIDTH_HALF = SCREEN_WIDTH / 2;
	private static final int SCREEN_HEIGHT_HALF = SCREEN_HEIGHT / 2;
	
	JButton playButton;
	JButton exitButton;
	JButton optionButton;
	Image img;
	SimpleDateFormat date = new SimpleDateFormat("[HH:mm:ss:SS]");
	
	public MenuFrame() throws GameException {
		
		System.out.println("[SNAKE]" + date.format(new Date()) + " << Creating launcher...");
		
		img = new ImageIcon(this.getClass().getResource("/snakeIcon.png")).getImage();
		
		/* PLAY BUTTON*/
		playButton = new JButton("PLAY");
		playButton.setBounds(SCREEN_WIDTH_HALF - 50, SCREEN_HEIGHT_HALF - 200, 100, 50);
		playButton.addActionListener(this);
		
		/*EXIT BUTTON*/
		exitButton = new JButton("EXIT");
		exitButton.setBounds(SCREEN_WIDTH_HALF - 50, SCREEN_HEIGHT_HALF + 150, 100, 50);
		exitButton.setBackground(Color.RED);
		exitButton.setForeground(Color.GREEN);
		exitButton.addActionListener(this);
		
		optionButton = new JButton("OPTIONS");
		optionButton.setBounds(SCREEN_WIDTH_HALF - 50, SCREEN_HEIGHT_HALF + 95, 100, 50);
		optionButton.addActionListener(this);
		
		
		// *** ! ! ! ! ! ! *** //
		JLabel highscore = new JLabel("Highscore: " + SnakeController.c.get("score", true) , JLabel.CENTER);
		highscore.setFont(new Font("Ink Free", Font.BOLD, 32));
		
		
		
		this.setTitle("Snake Launcher");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setIconImage(img);
		this.setVisible(true);
		
		
		/*ADD BUTTON'S TO FRAME*/
		this.add(playButton);
		this.add(exitButton);
		this.add(optionButton);
		this.add(highscore);
		
		
		System.out.println("[SNAKE]" + date.format(new Date()) + " << Launcher created");
		System.out.println("[SNAKE]" + date.format(new Date()) + " << Done!");
		System.out.println("[SNAKE]" + date.format(new Date()) + " << Press play to start...");	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playButton) {
			new GameFrame();
			setVisible(false); 
			dispose();
		}
		
		if(e.getSource() == exitButton) {
			System.exit(0);
		}
		
		if(e.getSource() == optionButton) {
			new OptionFrame();
			setVisible(false);
			dispose();
		}
	}
}
