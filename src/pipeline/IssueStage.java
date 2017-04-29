package pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.sun.xml.internal.ws.api.pipe.Pipe;

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

		if(inst == null || inst.opcode == CommonConstants.HLT){
			Pipeline.done = 1;
			return;
		}
		
		String pipelineType = inst.pipelineType;
		FunctionalUnit funit = FuntionalUnitManager.getFunctionalUnit(inst.pipelineType);
		if(funit == null){
			// write structural hazard
//			System.out.println("struct hazard for"  + inst.opcode);
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.STRUCT_COLUMN, 1);
			return;
		}
		
		// check for waw (destination is not being )
		for(Map.Entry<Integer, FunctionalUnit> m : instUnitmap.entrySet()){
			String destReg = CodeLoader.instMap.get(m.getKey()).getDestinationRegister();
			if(destReg != null && destReg.equals(inst.getDestinationRegister())){
				// write waw harzard
				//System.out.println("waw occured for " + CodeLoader.programStore.get(m.getKey()));
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.WAW_COLUMN, 1);
				return;
			}
		}
		
		//edge case
		String opcode = inst.opcode;
		if(opcode!=null && (opcode.equals(CommonConstants.LD) || opcode.equals(CommonConstants.SD))) // if double load and store then 2 ccycle req
			funit.executionTimeRequired = 2;
		
		String destReg = inst.getDestinationRegister();
		if(Register.isValidRegName(destReg)){
//			System.out.println("######################################");
//			System.out.println("Setting register write status for "+ CodeLoader.programStore.get(instIndex) + " at " + Test.clockCycle);
			Register.setRegWriteStatus(destReg);
			Register.setOwnerOfReg(destReg, instIndex);
		}
		
		instUnitmap.put(instIndex, funit);
		
		// instruction issued
		
//		System.out.println(instIndex);
		issueStageQueue.poll(); // instruction issued, remove the instruction from issue stage queue
		if(inst.opcode == CommonConstants.JUMP){
			String destLabel = inst.getDestinationRegister();
			int targetAddr = CodeLoader.labelMap.get(destLabel);
			Pipeline.instIndex = targetAddr;
		}
		Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.ISSUE_COLUMN, Test.clockCycle);
		DecodeStage.decStageQueue.add(scbdrowId);
		IssueStage.busy = false;
//		System.out.println("issue stage completed");
	}
}
