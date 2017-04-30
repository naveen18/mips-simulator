package pipeline;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import functionalunits.FunctionalUnit;
import main.Main;
import registers.Register;

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
		for(int i=0; i<decStageQueue.size(); i++){
			int scbdrowId = decStageQueue.get(i);
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			Instruction inst = CodeLoader.instMap.get(instIndex);
			// check raw hazard
			if(isRawHazard(instIndex)){
				// write raw hazard
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.RAW_COLUMN, 1);
				// check if next instruction can be decoded by continuing with the loop
				continue; 
			}
			
			if (inst.opcode.equals(CommonConstants.BEQ) || inst.opcode.equals(CommonConstants.BNE)) {
				if (isBranchTaken(inst)) { 
					// dont check structure hazard for BNE and BEQ
					int targetAddress = getTargetAddress(inst);
					// changed program counter in isBranchTaken function
					Pipeline.instIndex = targetAddress;
					IssueStage.issueStageQueue.clear();
					DecodeStage.decStageQueue.clear();
					IssueStage.busy = false;
					Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.DECODE_COLUMN, Main.clockCycle);
					Pipeline.oneCycleDelay = true;
				} else {
					Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.DECODE_COLUMN, Main.clockCycle);
					Pipeline.oneCycleDelay = true;
					decStageQueue.remove(i);
				}
				return;
			}
			decStageQueue.remove(i);
			ExecuteStage.execStageQueue.add(scbdrowId);
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.DECODE_COLUMN, Main.clockCycle);
			// decoded one instruction, done for this clock cycle
			break;
		}	
		
		//#####  check with prof if we need to copy source register values to stage buffer
		// to prevent war #####
	}
	
	public static boolean isRawHazard(int instIndex) throws Exception{
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
		if(regList == null || regList.size() < 2)
			throw new Exception("BEQ and BNE statement should have two registers");
		int reg1 = (int) Register.getRegister(regList.get(0));
		int reg2 = (int) Register.getRegister(regList.get(1));
		if((inst.opcode.equals(CommonConstants.BEQ) && reg1 == reg2) || (inst.opcode.equals(CommonConstants.BNE) && reg1 != reg2 )){
			return true;
		}
		return false;	
	}
	
	public static int getTargetAddress(Instruction inst) {
		// get target address for branching instruction
		String destLabel = inst.getDestinationRegister();
		int targetAddr = CodeLoader.labelMap.get(destLabel);
		return targetAddr;
	}
}
