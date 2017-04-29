package pipeline;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import functionalunits.FunctionalUnit;
import registers.Register;
import test.Test;

public class DecodeStage {
	public static List<Integer>  decStageQueue = new LinkedList<Integer>();
	private boolean busy;
	
	public boolean isBusy() {
		return busy;
	}
	
	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public static void decodeInstruction() throws Exception{
		if(decStageQueue.isEmpty())
			return;
		// check raw
		for(int i=0; i<decStageQueue.size(); i++){
			int scbdrowId = decStageQueue.get(i);
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			if(isRawHazard(instIndex)){
				// write raw hazard
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.RAW_COLUMN, 1);
				// check if next instruction can be decoded by continuing with the loop
				continue; 
			}
			decStageQueue.remove(i);
			//check for BEQ or BNE
			Instruction inst = CodeLoader.instMap.get(instIndex);
			if(inst.opcode.equals(CommonConstants.BEQ) || inst.opcode.equals(CommonConstants.BNE)){
				if(isBranchTaken(inst)){
					// flushed issue stage queue and changed program counter in isBranchTaken function
					return;
				}
			}
			ExecuteStage.execStageQueue.add(scbdrowId);
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.DECODE_COLUMN, Test.clockCycle);
			// decoded one instruction, done for this clock cycle
			break;
		}	
		
		//#####  check with prof if we need to copy source register values to stage buffer
		// to prevent war #####
	}
	
	public static boolean isRawHazard(int instIndex){
		// check if the source registers of this instruction is not being written
		if(CodeLoader.instMap.get(instIndex).getSourceRegisters() != null){
			for(String s : CodeLoader.instMap.get(instIndex).getSourceRegisters()){
				if( s!=null && !s.equals("") && Register.getRegWriteStatus(s) == true && Register.getOwnerOfReg(s) < instIndex){ 
					// if owner instruction > current instruction then its not a raw hazard
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isBranchTaken(Instruction inst) throws Exception{ // check for branch taken
		List<String> regList = inst.getSourceRegisters();
		int reg1 = (int) Register.getRegister(regList.get(0));
		if(regList == null || regList.size() < 2)
			throw new Exception("BEQ and BNE should have two registers");
		int reg2 = (int) Register.getRegister(regList.get(1));
		String destLabel = inst.getDestinationRegister();
		int targetAddr = CodeLoader.labelMap.get(destLabel);
		// if branch taken the flush the issueStage queue and set the program counter to target address
		if((inst.opcode.equals(CommonConstants.BEQ) && reg1 == reg2) || (inst.opcode.equals(CommonConstants.BNE) && reg1 != reg2 )){
			Pipeline.instIndex = targetAddr;
			IssueStage.issueStageQueue.clear();
			return true;
		}
		return false;	
	}
}
