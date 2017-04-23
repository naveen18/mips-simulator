package test;

import java.io.IOException;
import java.util.Map.Entry;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.Memory;
import common.constants.CommonConstants;
import pipeline.FetchStage;
import pipeline.IssueStage;

public class Test {
	public static int clockCycle = 0;
	public static void main(String args[]) throws Exception {
		AppConfig.updateConfig("/home/naveen/Desktop/Architecture/config.txt");
		CodeLoader.loadCode("/home/naveen/Desktop/Architecture/code.txt");
		//IssueStage.issueInstructions();
//		for (Entry<Integer, Instruction> inst : CodeLoader.instMap.entrySet()) {
//			System.out.println(inst.toString());
//		}
		try {
			Memory.readMemory("/home/naveen/Desktop/Architecture/data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// for (int i = CommonConstants.DATA_START_ADD; i <
		// CommonConstants.DATA_END_ADD; i++) {
		// System.out.println(Memory.getWord(i));
		// }

//		FetchStage fStage = FetchStage.getFstage();
//		int i = 0;
//		int instCount = CodeLoader.instMap.size();
//		while (i < instCount) {
//			clockCycle++;
//			if (!fStage.isBusy()) {
//				fStage.fetchInstruction(CodeLoader.instMap.get(i));
//				i++;
//			}
//		}
//		 for(Entry<Integer, Instruction> inst :
//		 CodeLoader.instMap.entrySet()){
//			 System.out.println(inst.toString());
//		 }

	}
}
