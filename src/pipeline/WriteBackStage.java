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
	public static Queue<Integer> prevIterQueue = new LinkedList<Integer>();

	public static void writebackInstruction() throws Exception {

		while (!prevIterQueue.isEmpty()) { // free the units and registers for
											// previous instruction in this
											// cycle to adjust delay
			int scbdrowId = prevIterQueue.poll();
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			functionalunits.FuntionalUnitManager.putFunctionalUnit(CodeLoader.instMap.get(instIndex).pipelineType);
			IssueStage.instUnitmap.remove(instIndex);
			Instruction inst = CodeLoader.instMap.get(instIndex);
			if (inst.getDestinationRegister() != null) {
				// free the destination register for previous iteration
				Register.unsetRegWriteStatus(inst.getDestinationRegister());
			}
		}

		while (!wbStageQueue.isEmpty()) { // mark the finish time of current
											// instructions and put them in the
											// prevIterQueue
											// to free their functional units
											// and registers in next clock cycle
			int currIterId = wbStageQueue.poll();
			int scbdrowId = currIterId;
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			CodeLoader.instMap.get(instIndex).write();
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.WRITEBACK_COLUMN, Main.clockCycle);
			prevIterQueue.add(scbdrowId);
		}
	}
}
