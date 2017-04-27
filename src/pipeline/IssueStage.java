package pipeline;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map.Entry;

import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import namedinstrucion.LI;
import functionalunits.FunctionalUnit;
import functionalunits.FuntionalUnitManager;
import test.Test;

public class IssueStage {
	public static boolean busy;
	public static Queue<Integer>  issueStageQueue = new LinkedList<Integer>();
	public static HashMap<Integer, FunctionalUnit> instUnitmap = new HashMap<>();
	
	public static void issueInstruction() {
		if(issueStageQueue.isEmpty())
			return;
		IssueStage.busy = true;
		//int finishTime = Test.clockCycle + 1;
		int instIndex = issueStageQueue.poll();
		Instruction inst = CodeLoader.instMap.get(instIndex);
		FunctionalUnit funit = FuntionalUnitManager.getFunctionalUnit(inst.pipelineType);
		if(funit == null){
			// write structiral hazard
			return;
		}
		instUnitmap.put(instIndex, funit);
		// instruction issued
		
//		System.out.println(instIndex);
		DecodeStage.decStageQueue.offer(instIndex);
//		System.out.println("issue stage completed");
	}
}
