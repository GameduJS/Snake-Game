package net.game.config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import net.game.Snake.SnakeController;
import net.game.functions.GameException;

public class DecodedConfigManager {
	
	
	public static HashMap<String, String> mapHelper = new HashMap<String, String>();


	public DecodedConfigManager() { }
	
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
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));

			try {
				output.write(toByteArray(mapHelper));
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
				
				BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
				Object readObject = input.read();
				input.close();

				if (!(readObject instanceof HashMap)) {
					throw new IOException("Data is not a HashMap");
				}

				mapHelper = (HashMap<String, String>) readObject;
				
			} catch (Exception e) {
				
			}
			
		}

	}
	
	
	public byte[] toByteArray(Object obj) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(obj);
		  out.flush();
		  return bos.toByteArray();
		} catch (IOException ex) {
			
		}
		return new byte[] {};
	}
}