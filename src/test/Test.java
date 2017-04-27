package test;

import java.io.IOException;
import java.util.Map.Entry;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.InstructionCache;
import common.Memory;
import common.constants.CommonConstants;
import pipeline.FetchStage;
import pipeline.IssueStage;
import pipeline.Pipeline;

public class Test {
	public static int clockCycle = 0;
	public static void main(String args[]) throws Exception {
		AppConfig.updateConfig("/Users/naveen/Desktop/Architecture/config.txt");
		CodeLoader.loadCode("/Users/naveen/Desktop/Architecture/code.txt");
		InstructionCache.initCache();
		try {
			Memory.readMemory("/Users/naveen/Desktop/Architecture/data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(AppConfig.getAppConfig().toString());
		Pipeline.startPipeLine();
	}
}
