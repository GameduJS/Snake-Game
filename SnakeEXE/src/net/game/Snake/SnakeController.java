package net.game.Snake;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.game.config.EncodedConfigManager;
import net.game.config.FileManager;
import net.game.functions.MenuFrame;

public class SnakeController {


	public static EncodedConfigManager c;
	public static FileManager manager;
	
	private static SimpleDateFormat date = new SimpleDateFormat("[HH:mm:ss:SS]");
	
	public static void main(String[] args) throws Exception {
		
		manager = new FileManager();
		
		
		c = new EncodedConfigManager();
		c.loadCurrencyFile();
		if(c.get("score", true) == null) {
			c.set("score", "0");
		}
		
		System.out.println("[SNAKE]" + date.format(new Date()) + " << Creating data-structure...");
		
		new MenuFrame();
	}
	
	public static File getScoreFile() {
		return manager.getScoreFile();
	}
	
	public static File getResourceFolder() {
		return manager.getResourceFolder();
	}
	
	public static EncodedConfigManager getLockedConfigManager() {
		return c;
	}
}