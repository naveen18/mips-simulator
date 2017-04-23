package pipeline;

import common.Instruction;

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

	public void decodeInstruction(Instruction inst){
		ExecuteStage exStage = new ExecuteStage(this.id);
	}
}
