package main;

import java.io.IOException;

import common.AppConfig;
import common.CodeLoader;
import cache.InstructionCache;
import common.Memory;
import functionalunits.FuntionalUnitManager;
import pipeline.Pipeline;
import util.Utilities;

public class Main {
	public static int clockCycle = 0;
	public static void main(String args[]) throws Exception {
		if(args.length < 4){
			throw new Exception("Number of arguements can not be less than 4");
		}
		System.out.println("mail file begin");
		AppConfig.updateConfig(args[2]);
		CodeLoader.loadCode(args[0]);
		InstructionCache.initCache();
		FuntionalUnitManager.initFuntionalUnits();
		try {
			Memory.readMemory(args[1]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pipeline.startPipeLine();
		Utilities.printScoreBoard(args[3]);
		Utilities.storeScoreBoard(args[3]);
	}
}
