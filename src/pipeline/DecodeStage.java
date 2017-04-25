package pipeline;

import common.Instruction;
import test.Test;

public class DecodeStage {
	public int id;
	private boolean busy;

	public DecodeStage(int id){
		this.id = id;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public void decodeInstruction(Instruction inst, IssueStage is){
		ExecuteStage exStage = new ExecuteStage(this.id);
		int val = Test.clockCycle;
		while(Test.clockCycle <= val);
		exStage.executeInstruction(inst, is);
	}
}
