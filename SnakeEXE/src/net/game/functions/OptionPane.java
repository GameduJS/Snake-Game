package net.game.functions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class OptionPane extends JPanel implements ActionListener {
	
	private static final int SCREEN_WIDTH = 600;
	private static final int SCREEN_HEIGHT = 600;
	
	private static final int SCREEN_WIDTH_HALF = SCREEN_WIDTH / 2;
	private static final int SCREEN_HEIGHT_HALF = SCREEN_HEIGHT / 2;
	
	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 50;
	
	private static final int BUTTON_X = SCREEN_WIDTH_HALF - (BUTTON_WIDTH / 2);
	@SuppressWarnings("unused")
	private static final int BUTTON_Y = SCREEN_HEIGHT_HALF - (BUTTON_HEIGHT / 2); 
	
	JButton backButton;
//	JLabel label;
	
	JButton greenColorButton;
	JButton redColorButton;
	
	Font font;
	
	public OptionPane() {
				
		font = new Font("Minecraft", Font.BOLD, 20);
		
		/* PLAY BUTTON*/
		backButton = new JButton("BACK");
		backButton.setFont(font);
		backButton.setBounds(BUTTON_X, SCREEN_HEIGHT - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		backButton.addActionListener(this);
		
//		label = new JLabel(" ");
//		label.setBackground(Color.GREEN);
//		label.setOpaque(true);
//		label.setBounds(BUTTON_X, SCREEN_HEIGHT_HALF, 50, 20);
		
		greenColorButton = new JButton("Green");
		greenColorButton.setFont(font);
		greenColorButton.setBounds(SCREEN_WIDTH_HALF - 150, SCREEN_HEIGHT_HALF - 100, 100, 50);
		greenColorButton.setBackground(Color.GREEN);
		greenColorButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue)));
		greenColorButton.addActionListener(this);
		
		redColorButton = new JButton("Red");
		redColorButton.setFont(font);
		redColorButton.setBounds(SCREEN_WIDTH_HALF + 150, SCREEN_HEIGHT_HALF - 100, 100, 50);
		redColorButton.setBackground(Color.RED);
		
		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		this.setBackground(new Color(238, 238, 238));
		this.setLayout(null);
		
		addButtons();
		
	}
	
	private void addButtons() {
		
		this.add(greenColorButton);
		this.add(backButton);
//		this.add(label);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		
		if(e.getSource() instanceof JLabel) {
			System.out.println("HEY");
		}
		
		
		if(e.getSource() instanceof JButton) {
			
			if(e.getSource().equals(backButton)) {
				
				new ComponentDisposer(this);
				
				try {
					new MenuFrame();
				} catch (GameException ex) {
					ex.printStackTrace();
				}
			}
			
			if(e.getSource().equals(greenColorButton)) {
				
				
				
			}
			
		}
		
	}
}