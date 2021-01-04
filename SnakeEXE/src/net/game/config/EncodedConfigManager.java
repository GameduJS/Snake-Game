package net.game.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.game.Snake.SnakeController;
import net.game.functions.GameException;

public class EncodedConfigManager {
	
	
	public static HashMap<String, String> mapHelper = new HashMap<String, String>();


	public EncodedConfigManager() { }
	
	public void set(String key, String value) {
		mapHelper.put(key, value);
	}
	
	public String get(String key, boolean returnNull) throws GameException {
		if(mapHelper.containsKey(key)) {
			return mapHelper.get(key);
		}
		if(!returnNull) {
			throw new GameException("Key is not avaible: " + key);
		}
		return null;
	}
	
	
	public String get(String key) throws GameException {
		if(mapHelper.containsKey(key)) {
			return mapHelper.get(key);
		}
			throw new GameException("Key is not avaible: " + key);
	}
	
	

	public void saveCurrencyFile() throws FileNotFoundException, IOException {
		File file = SnakeController.getScoreFile();
		ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));

			try {
				output.writeObject(mapHelper);
				output.flush();
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	
	@SuppressWarnings("unchecked")
	public void loadCurrencyFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = SnakeController.getScoreFile();
		if (file != null) {

			try {
				
				ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
				Object readObject = input.readObject();
				input.close();

				if (!(readObject instanceof HashMap)) {
					throw new IOException("Data is not a HashMap");
				}

				mapHelper = (HashMap<String, String>) readObject;
				
			} catch (Exception e) {
				
			}
			
		}

	}
}