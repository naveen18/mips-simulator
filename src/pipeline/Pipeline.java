package pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.InstructionCache;
import common.constants.CommonConstants;
import test.Test;

public class Pipeline {
	public static HashMap<Integer, Integer> scobdIdtoInstId = new HashMap<>();
	public static ArrayList<List<Integer>> scoreboard = new ArrayList<List<Integer>>(); 
	public static int scoreboardRowId = 0;
	public static int instIndex;
	public static int done = 0;
	public static void startPipeLine() {
		while(done!=1) {
			Test.clockCycle++;
			WriteBackStage.writebackInstruction();
			ExecuteStage.executeInstruction();
			DecodeStage.decodeInstruction();
			IssueStage.issueInstruction();
			FetchStage.fetchInstruction(Pipeline.instIndex);
			if(Test.clockCycle > 100)
				break;
		}
	}
}
