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
		FunctionalUnit funit = FuntionalUnitManager.getFunctionalUnit(pipelineType);
		if(funit == null){
			// if unit not available then write structure hazard
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.STRUCT_COLUMN, 1);
			return;
		}
		
		// check for waw (destination is not being )
		for(Map.Entry<Integer, FunctionalUnit> m : instUnitmap.entrySet()){
			String destReg = CodeLoader.instMap.get(m.getKey()).getDestinationRegister();
			if(destReg != null && destReg.equals(inst.getDestinationRegister())){
				// write waw harzard
				// put back the funtional unit in pool
				FuntionalUnitManager.putFunctionalUnit(inst.pipelineType);
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.WAW_COLUMN, 1);
				return;
			}
		}
		
		//edge case
		String opcode = inst.opcode;
		if(opcode!=null && (opcode.equals(CommonConstants.LD) || opcode.equals(CommonConstants.SD))) // if double load and store then 2 cycle required
			funit.executionTimeRequired = 2;
		
		String destReg = inst.getDestinationRegister();
		if(Register.isValidRegName(destReg)){
			Register.setRegWriteStatus(destReg);
			Register.setOwnerOfReg(destReg, instIndex);
		}
		// put the inst with the corresponding unit alloted to it in map
		instUnitmap.put(instIndex, funit);
		//System.out.println(instIndex + "inst issued");
		
		// instruction issued, remove the instruction from issue stage queue
		issueStageQueue.poll();
		// if instruction is jump then change the program counter to target address
		if(inst.opcode == CommonConstants.JUMP){
			String destLabel = inst.getDestinationRegister();
			int targetAddr = CodeLoader.labelMap.get(destLabel);
			Pipeline.instIndex = targetAddr;
		}
		Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.ISSUE_COLUMN, Test.clockCycle);
		DecodeStage.decStageQueue.add(scbdrowId);
		IssueStage.busy = false;
	}
}
