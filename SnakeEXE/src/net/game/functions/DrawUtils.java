package net.game.functions;

import java.awt.Color;
import java.awt.Graphics;

public class DrawUtils{

	private static Graphics g;
	
	public static void setGraphics(Graphics graphics) {
		g = graphics;
	}
	
	public static void drawGoldenApple(int x, int y, int width, int height) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, width, height);
	}
	
	public static void drawApple(int x, int y, int width, int height) {
		g.setColor(Color.RED);
		g.fillOval(x, y, width, height);
	}
	
	public static void drawBodyPart(Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
	}
}
