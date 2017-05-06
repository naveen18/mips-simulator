package pipeline;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.xml.crypto.Data;

import cache.DataCache;
import cache.DataCacheSet;
import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.Memory;
import common.constants.CommonConstants;
import functionalunits.FunctionalUnit;
import main.Main;
import namedinstrucion.LW;
import registers.Register;
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
				}
			} else {
				f.currExecutionTime++;
			}
			// if the execution time is complete for an instruction then push
		    // it to writeback stage
			if (f.currExecutionTime == f.executionTimeRequired) {
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
	
}
