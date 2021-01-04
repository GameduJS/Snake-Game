package net.game.functions;

import java.awt.Component;
import java.awt.Window;

import javax.swing.SwingUtilities;

public class ComponentDisposer {

	public ComponentDisposer(Component c) {
		Window win = SwingUtilities.getWindowAncestor(c);
		win.dispose();
	}
	
}
