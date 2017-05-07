package pipeline;

import java.util.LinkedList;
import java.util.List;

import cache.DataCache;
import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import functionalunits.FunctionalUnit;
import main.Main;
import util.Utilities;

public class ExecuteStage {
	public static List<Integer> execStageQueue = new LinkedList<Integer>();

	public static void executeInstruction() throws Exception {
		// if no instruction to execute then stall
		if (execStageQueue.isEmpty())
			return;
		// execute all the instruction available for one clock cycle
		for (int i = 0; i < execStageQueue.size(); i++) {
			int scbdrowId = execStageQueue.get(i);
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			FunctionalUnit f = IssueStage.instUnitmap.get(instIndex);
			Instruction inst = CodeLoader.instMap.get(instIndex);
			if (AppConfig.appConfig.isCacheOn) {
				if (handleLoadStore(inst, scbdrowId)) {
					f.currExecutionTime++;
				} else{
					continue;
				}
			} else {
				f.currExecutionTime++;
			}
			if (busSyncCheck(inst)) { // very imp edge case to check if LD or SD finished their first exec cycle while waiting for icache 
				f.executionTimeRequired--;
			}
			// if the execution time is complete for an instruction then push
		    // it to writeback stage
			if (f.currExecutionTime == f.executionTimeRequired) {
				if(Utilities.isLdorSd(inst)){
					//reset the parameters used for synch logic
					Controller.firstWordMiss = false;
					Controller.secondWordMiss = false;
					Controller.iCacheHappended = false;
				}
				DcacheProcess.countDcacheRequests(inst);
				CodeLoader.instMap.get(instIndex).execute();
				execStageQueue.remove(i);
				i--;// when you remove an element from the queue the elements after the removed element gets shifted by 1.
				WriteBackStage.wbStageQueue.offer(scbdrowId);
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.EXECUTE_COLUMN, Main.clockCycle);
			}
		}
	}
	
	public static boolean handleLoadStore(Instruction inst, int scbdrowId) throws Exception {
		if(inst.opcode.equals(CommonConstants.LD)) {
			return Controller.controlLoadDouble(inst, scbdrowId);
		} else if(inst.opcode.equals(CommonConstants.LW)){
			return Controller.controlLoadWord(inst, scbdrowId);
		} else if(inst.opcode.equals(CommonConstants.SW)){
			return Controller.controlStoreWord(inst, scbdrowId);
		} else if(inst.opcode.equals(CommonConstants.SD)){
			return Controller.controlStoreDouble(inst, scbdrowId);
		} else{
			// inst is not of Load store type, hence data avaiable and return true
			return true;
		}
	}
	
	public static boolean busSyncCheck(Instruction inst){
		return Utilities.isLdorSd(inst) && overlapIcacheDcacheCondition(inst);
	}
	
	public static boolean overlapIcacheDcacheCondition(Instruction inst){
		return (!Controller.firstWordMiss && Controller.secondWordMiss && Controller.iCacheHappended);
	}
}
