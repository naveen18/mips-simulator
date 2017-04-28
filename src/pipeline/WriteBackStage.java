package pipeline;

import java.util.LinkedList;
import java.util.Queue;

import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import functionalunits.*;
import test.Test;
import pipeline.FetchStage;
import registers.Register;

public class WriteBackStage {
	public static Queue<Integer>  wbStageQueue = new LinkedList<Integer>();
	
	
	public static void writebackInstruction(){
		// perform task
		if(wbStageQueue.isEmpty())
			return;
		int scbdrowId = wbStageQueue.poll();
		int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
		functionalunits.FuntionalUnitManager.putFunctionalUnit(CodeLoader.instMap.get(instIndex).pipelineType);
		CodeLoader.instMap.get(instIndex).write();
		Instruction inst = CodeLoader.instMap.get(instIndex);
		// reg write is complete, unset the reg busy status
		if(inst.getDestinationRegister() != null)
			Register.unsetRegWriteStatus(inst.getDestinationRegister());
		Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.WRITEBACK_COLUMN, Test.clockCycle);
	}
}
