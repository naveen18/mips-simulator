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
import main.Main;

public class ExecuteStage {
	public static List<Integer> execStageQueue = new LinkedList<Integer>();

	public static void executeInstruction() {
		// if no instruction to execute then stall
		if (execStageQueue.isEmpty())
			return;
		// execute all the instruction available for one clock cycle
		for (int i = 0; i < execStageQueue.size(); i++) {
			int scbdrowId = execStageQueue.get(i);
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			FunctionalUnit f = IssueStage.instUnitmap.get(instIndex);
			f.currExecutionTime++;
			// if the execution time is complete for an instruction then push
			// it to writeback stage
			if (f.currExecutionTime == f.executionTimeRequired) {
				CodeLoader.instMap.get(i).execute();
				execStageQueue.remove(i);
				WriteBackStage.wbStageQueue.offer(scbdrowId);
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.EXECUTE_COLUMN, Main.clockCycle);
			}
		}
	}
}
