package pipeline;

import java.util.LinkedList;
import java.util.Queue;

import common.AppConfig;
import common.Instruction;
import common.constants.CommonConstants;
import test.Test;

public class ExecuteStage {
	public static Queue<Integer>  execStageQueue = new LinkedList<Integer>();
	public static void executeInstruction() {
		if(execStageQueue.isEmpty())
			return;
		int instIndex = execStageQueue.poll();
		WriteBackStage.wbStageQueue.offer(instIndex);
//		System.out.println("execute stage completed");
	}

	public static int getExecutionTime(Instruction inst) {
		int count = 1;
		if (inst.pipelineType.equals(CommonConstants.FPADDER))
			count = AppConfig.appConfig.getNumFpAdderCycleCount();
		else if (inst.pipelineType.equals(CommonConstants.FPMULTIPLIER))
			count = AppConfig.appConfig.getNumFpMultiplierCycleCount();
		else if (inst.pipelineType.equals(CommonConstants.FPDIVIDER))
			count = AppConfig.appConfig.getNumFpDividerCycleCount();
		return count;
	}
}
