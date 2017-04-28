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

	public static void decodeInstruction(){
		if(decStageQueue.isEmpty())
			return;
		// check raw
		
		for(int i=0; i<decStageQueue.size(); i++){
			int scbdrowId = decStageQueue.get(i);
			int instIndex = Pipeline.scobdIdtoInstId.get(scbdrowId);
			if(isRawHazard(instIndex)){
				// write raw hazard
				Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.RAW_COLUMN, 1);
				continue; // check if next instruction can be decoded
			}
			decStageQueue.remove(i);
			ExecuteStage.execStageQueue.add(scbdrowId);
			Pipeline.scoreboard.get(scbdrowId).set(CommonConstants.DECODE_COLUMN, Test.clockCycle);
			// decoded one instruction, done for this clock cycle
			break;
		}	

		//#####  check with prof if we need to copy source register values to stage buffer
		// to prevent war #####
		
		//System.out.println("decode stage completed");
	}
	
	public static boolean isRawHazard(int instIndex){
		// check if the source registers of this instruction is not being written
		if(CodeLoader.instMap.get(instIndex).getSourceRegisters() != null){
			for(String s : CodeLoader.instMap.get(instIndex).getSourceRegisters()){
				if( s!=null && Register.getRegWriteStatus(s))
					return true;
			}
		}
		return false;
	}
}
