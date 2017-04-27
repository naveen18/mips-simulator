package pipeline;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.InstructionCache;
import common.constants.CommonConstants;
import test.Test;

public class Pipeline {

	public static int instIndex;
	public static void startPipeLine() {
		int done=0;
		while(done!=1) {
			Test.clockCycle++;
			WriteBackStage.writebackInstruction();
			ExecuteStage.executeInstruction();
			DecodeStage.decodeInstruction();
			IssueStage.issueInstruction();
			FetchStage.fetchInstruction(Pipeline.instIndex);
			if(Test.clockCycle>60)
				break;
		}
	}
}
