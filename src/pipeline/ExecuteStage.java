package pipeline;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;



import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import functionalunits.FunctionalUnit;
import test.Test;

public class ExecuteStage {
	public static List<Integer> execStageQueue = new LinkedList<Integer>();
	public static void executeInstruction() {
		if(execStageQueue.isEmpty())
			return;
		
		for(int i=0; i<execStageQueue.size(); i++){
			int scbdrowId = execStageQueue.get(i);
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			FunctionalUnit f = IssueStage.instUnitmap.get(instIndex);
			f.currExecutionTime++;
			System.out.println(f.currExecutionTime);
			if(f.currExecutionTime == f.executionTimeRequired){
				System.out.println("execution finished for " + instIndex + " at " + Test.clockCycle);
				CodeLoader.instMap.get(i).execute();
				execStageQueue.remove(i);
				WriteBackStage.wbStageQueue.offer(scbdrowId);
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.EXECUTE_COLUMN, Test.clockCycle);
			}
		}
//		System.out.println("execute stage completed");
	}

//	public static int getExecutionTime(Instruction inst) {
//		int count = 1;
//		if (inst.pipelineType.equals(CommonConstants.FPADDER))
//			count = AppConfig.appConfig.getNumFpAdderCycleCount();
//		else if (inst.pipelineType.equals(CommonConstants.FPMULTIPLIER))
//			count = AppConfig.appConfig.getNumFpMultiplierCycleCount();
//		else if (inst.pipelineType.equals(CommonConstants.FPDIVIDER))
//			count = AppConfig.appConfig.getNumFpDividerCycleCount();
//		return count;
//	}
	
}
