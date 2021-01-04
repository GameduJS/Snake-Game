package net.game.functions;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class OptionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	
	public OptionFrame() {
		
		Image img = new ImageIcon(this.getClass().getResource("/snakeIcon.png")).getImage();

		this.add(new OptionPane());	
		this.setTitle("Snake Options");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(img);
		
	}
	
	
}
