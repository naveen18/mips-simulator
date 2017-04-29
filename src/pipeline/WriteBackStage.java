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
	public static int prevIterId = -1;
	public static int currIterId = -1;
	public static boolean currIdChanged = false; // to check if the currIterId is updated or not
	
	public static void writebackInstruction(){
		// perform task
		
		if(!wbStageQueue.isEmpty()){
			currIterId = wbStageQueue.poll();
			int scbdrowId = currIterId;
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			CodeLoader.instMap.get(instIndex).write();
			Instruction inst = CodeLoader.instMap.get(instIndex);
			if(inst.getDestinationRegister() != null){
//				System.out.println(Test.clockCycle);
//				System.out.println(" unsetting register status " + inst.getDestinationRegister() + " by " + CodeLoader.programStore.get(instIndex));
				Register.unsetRegWriteStatus(inst.getDestinationRegister());
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.WRITEBACK_COLUMN, Test.clockCycle);
			}
			currIdChanged = true;
		}
		
		if(prevIterId != -1){
			//.out.println("prev id " + prevIterId);
			//System.out.println("curr id " + currIterId);
			//System.out.println("entered  at " + Test.clockCycle);
			int scbdrowId = prevIterId;
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			// put back functional unit in pool
			//System.out.println("putting back " + CodeLoader.instMap.get(instIndex).pipelineType + " at " + Test.clockCycle);
			functionalunits.FuntionalUnitManager.putFunctionalUnit(CodeLoader.instMap.get(instIndex).pipelineType);
			// remove the completed instruction from instruction funUnit map
			IssueStage.instUnitmap.remove(instIndex);
			//CodeLoader.instMap.get(instIndex).write();
			Instruction inst = CodeLoader.instMap.get(instIndex);
			prevIterId = -1;
		}
		if(currIdChanged) prevIterId = currIterId;
		currIdChanged = false;
	}
}
