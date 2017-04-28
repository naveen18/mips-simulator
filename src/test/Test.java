package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.InstructionCache;
import common.Memory;
import common.constants.CommonConstants;
import functionalunits.FuntionalUnitManager;
import pipeline.FetchStage;
import pipeline.IssueStage;
import pipeline.Pipeline;

public class Test {
	public static int clockCycle = 0;
	public static void main(String args[]) throws Exception {
		AppConfig.updateConfig("/Users/naveen/Desktop/Architecture/config.txt");
		CodeLoader.loadCode("/Users/naveen/Desktop/Architecture/code.txt");
		InstructionCache.initCache();
		FuntionalUnitManager.initFuntionalUnits();
		try {
			Memory.readMemory("/Users/naveen/Desktop/Architecture/data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(AppConfig.getAppConfig().toString());
		Pipeline.startPipeLine();
		printScoreBoard();
	}
	
	public static void printScoreBoard(){
//		ArrayList<Integer> l = new ArrayList<>();
//		//l.add(4,-1);
//		System.out.println(l);
		for(int i=0; i<Pipeline.scoreboard.size(); i++){
			System.out.print(CodeLoader.instMap.get(Pipeline.scobdIdtoInstId.get(i)));
			for(int j=0; j<Pipeline.scoreboard.get(i).size(); j++){
				System.out.print("   " + Pipeline.scoreboard.get(i).get(j));
			}
			System.out.println();
		}
	}
}
