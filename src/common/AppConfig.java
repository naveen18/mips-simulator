package common;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AppConfig {

	private static AppConfig appConfig;

	private int numFpAdderUnits = 0;
	private int numFpMultiplierUnits = 0;
	private int numFpDividerUnits = 0;
	private int numCacheBlock = 0;
	
	private int numFpAdderCycleCount = 0;
	private int numFpMultiplierCycleCount = 0;
	private int numFpDividerCycleCount = 0;
	private int blockSizeInWords = 0;

	public int getNumFpAdderUnits() {
		return this.numFpAdderUnits;
	}

	public void setNumFpAdderUnits(int numFpAdderUnits) {
		this.numFpAdderUnits = numFpAdderUnits;
	}

	public int getNumFpMultiplierUnits() {
		return this.numFpMultiplierUnits;
	}

	public void setNumFpMultiplierUnits(int numFpMultiplierUnits) {
		this.numFpAdderUnits = numFpMultiplierUnits;
	}

	public int getNumFpDividerUnits() {
		return this.numFpDividerUnits;
	}

	public void setNumFpDividerUnits(int numFpDividerUnits) {
		this.numFpDividerUnits = numFpDividerUnits;
	}

	public int getNumCacheBlock() {
		return this.numCacheBlock;
	}

	public void setNumCacheBlock(int numCacheBlock) {
		this.numCacheBlock = numCacheBlock;
	}
	
	public int getNumFpDividerCycleCount() {
		return this.numFpDividerCycleCount;
	}

	public void setNumFpDividerCycleCount(int numFpDividerCycleCount) {
		this.numFpDividerCycleCount = numFpDividerCycleCount;
	}

	public int getNumFpMultiplierCycleCount() {
		return this.numFpMultiplierCycleCount;
	}

	public void setNumFpMultiplierCycleCount(int numFpMultiplierCycleCount) {
		this.numFpMultiplierCycleCount = numFpMultiplierCycleCount;
	}

	public int getNumFpAdderCycleCount() {
		return this.numFpAdderCycleCount;
	}

	public void setNumFpAdderCycleCount(int numFpAdderCycleCount) {
		this.numFpAdderCycleCount = numFpAdderCycleCount;
	}

	public int getBlockSizeInWords() {
		return this.blockSizeInWords;
	}

	public void setBlockSizeInWords(int blockSizeInWords) {
		this.blockSizeInWords = blockSizeInWords;
	}


	
	public static AppConfig getAppConfig() {
		if (appConfig == null) {
			appConfig = new AppConfig();
			
		}
		return appConfig;
	}

	public void setAppConfig(AppConfig appconfig) {
		appConfig = appconfig;
	}

	public static void updateConfig(String fileName) {
		
		AppConfig appConfig = getAppConfig();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			if (line != null && line != "") {
				String[] parts = line.split("[:,]");
				appConfig.setNumFpAdderUnits(Integer.parseInt(parts[1].trim()));
				appConfig.setNumFpAdderCycleCount(Integer.parseInt(parts[2].trim()));
			}
			
			line = br.readLine();
			if (line != null && line != "") {
				String[] parts = line.trim().split("[:,]");
				appConfig.setNumFpMultiplierUnits(Integer.parseInt(parts[1].trim()));
				appConfig.setNumFpMultiplierCycleCount(Integer.parseInt(parts[2].trim()));
			}
			
			line = br.readLine();
			if (line != null && line != ""){
				String[] parts = line.trim().split("[:,]");
				appConfig.setNumFpDividerUnits(Integer.parseInt(parts[1].trim()));
				appConfig.setNumFpDividerCycleCount(Integer.parseInt(parts[2].trim()));
			}
			
			line = br.readLine();
			if (line != null && line != "") {
				String[] parts = line.trim().split("[:,]");
				appConfig.setNumCacheBlock(Integer.parseInt(parts[1].trim()));
				appConfig.setBlockSizeInWords(Integer.parseInt(parts[2].trim()));
			}
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
