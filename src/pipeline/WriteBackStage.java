package pipeline;

import java.util.LinkedList;
import java.util.Queue;

import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import functionalunits.*;
import main.Main;
import pipeline.FetchStage;
import registers.Register;

public class WriteBackStage {
	public static Queue<Integer> wbStageQueue = new LinkedList<Integer>();
	public static int prevIterId = -1;
	public static int currIterId = -1;
	public static boolean currIdChanged = false; // to check if the currIterId
													// is updated or not

	public static void writebackInstruction() {
		// in current iteration only write the finish clock cycle.
		if (!wbStageQueue.isEmpty()) {
			currIterId = wbStageQueue.poll();
			int scbdrowId = currIterId;
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			CodeLoader.instMap.get(instIndex).write();
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.WRITEBACK_COLUMN, Main.clockCycle);
			currIdChanged = true;
		}
		if (prevIterId != -1) {
			// put back functional unit in pool for previous iteration
			int scbdrowId = prevIterId;
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			functionalunits.FuntionalUnitManager.putFunctionalUnit(CodeLoader.instMap.get(instIndex).pipelineType);
			IssueStage.instUnitmap.remove(instIndex);
			Instruction inst = CodeLoader.instMap.get(instIndex);
			if (inst.getDestinationRegister() != null) {
				// free the destination register for previous iteration
				Register.unsetRegWriteStatus(inst.getDestinationRegister());
			}
			prevIterId = -1;
		}
		if (currIdChanged)
			prevIterId = currIterId;
		currIdChanged = false;
	}
}
