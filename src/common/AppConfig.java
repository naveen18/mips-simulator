package common;

import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import common.constants.CommonConstants;

public class AppConfig {

	private static AppConfig appConfig;

	public static final HashMap<String, Integer> opcodeMap = new HashMap<>();
	public static final HashMap<String, Integer> rCodeMap = new HashMap<>();

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

	public static void fillOpcodeMap() {
		opcodeMap.put(CommonConstants.LW, 0);
		opcodeMap.put(CommonConstants.SW, 1);
		opcodeMap.put(CommonConstants.LD, 2);
		opcodeMap.put(CommonConstants.SD, 3);

		opcodeMap.put(CommonConstants.DADD, 4);
		opcodeMap.put(CommonConstants.DADDI, 5);
		opcodeMap.put(CommonConstants.DSUB, 6);
		opcodeMap.put(CommonConstants.DSUBI, 7);
		opcodeMap.put(CommonConstants.AND, 8);
		opcodeMap.put(CommonConstants.ANDI, 9);
		opcodeMap.put(CommonConstants.OR, 10);
		opcodeMap.put(CommonConstants.ORI, 11);
		opcodeMap.put(CommonConstants.LI, 12);
		opcodeMap.put(CommonConstants.LUI, 13);
		opcodeMap.put(CommonConstants.ADDD, 14);
		opcodeMap.put(CommonConstants.MULTD, 15);
		opcodeMap.put(CommonConstants.DIVD, 16);
		opcodeMap.put(CommonConstants.SUBD, 17);

		opcodeMap.put(CommonConstants.JUMP, 18);
		opcodeMap.put(CommonConstants.BEQ, 19);
		opcodeMap.put(CommonConstants.BNE, 20);

		opcodeMap.put(CommonConstants.HLT, 21);
	}

	public static void fillRcodeMap() {
		rCodeMap.put(CommonConstants.R1, 0);
		rCodeMap.put(CommonConstants.R2, 1);
		rCodeMap.put(CommonConstants.R3, 2);
		rCodeMap.put(CommonConstants.R4, 3);
		rCodeMap.put(CommonConstants.R5, 4);
		rCodeMap.put(CommonConstants.R6, 5);
		rCodeMap.put(CommonConstants.R7, 6);
		rCodeMap.put(CommonConstants.R8, 7);
		rCodeMap.put(CommonConstants.R9, 8);
		rCodeMap.put(CommonConstants.R10, 9);
		rCodeMap.put(CommonConstants.R11, 10);
		rCodeMap.put(CommonConstants.R12, 11);
		rCodeMap.put(CommonConstants.R13, 12);
		rCodeMap.put(CommonConstants.R14, 13);
		rCodeMap.put(CommonConstants.R15, 14);
		rCodeMap.put(CommonConstants.R16, 15);
		rCodeMap.put(CommonConstants.R17, 16);
		rCodeMap.put(CommonConstants.R18, 17);
		rCodeMap.put(CommonConstants.R19, 18);
		rCodeMap.put(CommonConstants.R20, 19);
		rCodeMap.put(CommonConstants.R21, 20);
		rCodeMap.put(CommonConstants.R22, 21);
		rCodeMap.put(CommonConstants.R23, 22);
		rCodeMap.put(CommonConstants.R24, 23);
		rCodeMap.put(CommonConstants.R25, 24);
		rCodeMap.put(CommonConstants.R26, 25);
		rCodeMap.put(CommonConstants.R27, 26);
		rCodeMap.put(CommonConstants.R28, 27);
		rCodeMap.put(CommonConstants.R29, 28);
		rCodeMap.put(CommonConstants.R30, 29);
		rCodeMap.put(CommonConstants.R31, 30);
		rCodeMap.put(CommonConstants.R32, 31);
	}

	public static int getRcode(String rName) {
		if (opcodeMap.containsKey(rName))
			return rCodeMap.get(rName);
		else
			return -1;
	}

	public static int getOpcode(String instuc) {
		if (opcodeMap.containsKey(instuc))
			return opcodeMap.get(instuc);
		else
			return -1;
	}

	public static void updateConfig(String fileName) {

		AppConfig appConfig = getAppConfig();

		fillOpcodeMap();
		fillRcodeMap();

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
			if (line != null && line != "") {
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
