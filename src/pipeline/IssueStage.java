package pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Map.Entry;

import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import namedinstrucion.LI;
import registers.Register;
import functionalunits.FunctionalUnit;
import functionalunits.FuntionalUnitManager;
import test.Test;

public class IssueStage {
	public static boolean busy;
	public static Queue<Integer>  issueStageQueue = new LinkedList<Integer>();
	public static HashMap<Integer, FunctionalUnit> instUnitmap = new HashMap<>();
	
	public static void issueInstruction() {
		if(issueStageQueue.isEmpty()){
			return;
		}
		int scbdrowId = issueStageQueue.peek();
		int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
		Instruction inst = CodeLoader.instMap.get(instIndex);
		// check for waw (destination is not being )
		for(Map.Entry<Integer, FunctionalUnit> m : instUnitmap.entrySet()){
			if(CodeLoader.instMap.get(m.getKey()).getDestinationRegister().equals(inst.getDestinationRegister())){
				// write waw harzard
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.WAW_COLUMN, 1);
				return;
			}
		}
		FunctionalUnit funit = FuntionalUnitManager.getFunctionalUnit(inst.pipelineType);
		if(funit == null){
			// write structural hazard
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.STRUCT_COLUMN, 1);
			return;
		}
		
		//edge case
		if(inst.opcode == CommonConstants.LD || inst.opcode == CommonConstants.SD) // if double load and store then 2 ccycle req
			funit.executionTimeRequired = 2;
		
		instUnitmap.put(instIndex, funit);
		String destReg = inst.getDestinationRegister();
		if(destReg != null)
			Register.setRegWriteStatus(destReg);
		// instruction issued
		
//		System.out.println(instIndex);
		issueStageQueue.poll(); // instruction issued, remove the instruction from issue stage queue
		Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.ISSUE_COLUMN, Test.clockCycle);
		DecodeStage.decStageQueue.add(scbdrowId);
		IssueStage.busy = false;
//		System.out.println("issue stage completed");
	}
}
