package net.game.functions;

import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import net.game.Snake.Game;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat date = new SimpleDateFormat("[HH:mm:ss:SS]");
	
	public GameFrame() {

		System.out.println("[SNAKE]" + date.format(new Date()) + " << Create Snake Game...");
		
		Image img = new ImageIcon(this.getClass().getResource("/snakeIcon.png")).getImage();
		
		System.gc();

		this.add(new Game());	
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(img);
	}
}