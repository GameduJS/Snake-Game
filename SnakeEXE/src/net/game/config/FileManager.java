package net.game.config;

import java.io.File;
import java.io.IOException;

public class FileManager {

	/* FOLDER */
	static File appdataFolder, resourceFolder;
	
	/* FILE */
	static File score;
	
	public FileManager() {
		
		appdataFolder = new File(System.getenv("APPDATA") + "/Snake");
		resourceFolder = new File(appdataFolder, "resources");
		score = new File(resourceFolder, "/HighScore.txt");
		
		new FolderManager();
		new FileChecker();
	}
	
	
	public File getScoreFile() { return score; }
	public File getResourceFolder() { return resourceFolder; }
	
	
	public class FolderManager {
		
		
		public FolderManager() {
			
			if(!appdataFolder.exists()) appdataFolder.mkdir();
			if(!resourceFolder.exists()) resourceFolder.mkdir();
			
		}
	}
	
	public class FileChecker {
		
		public FileChecker() {
			
			if(!score.exists()) try { score.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
			
		}
		
	}
}

