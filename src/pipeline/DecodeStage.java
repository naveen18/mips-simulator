package pipeline;

import common.Instruction;

public class DecodeStage {
	private static DecodeStage decStage = null;
	private boolean busy;

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public static DecodeStage getDecStage() {
		if (decStage == null)
			decStage = new DecodeStage();
		return decStage;
	}
	
	public void decodeInstruction(Instruction inst){
		ExecuteStage exStage = ExecuteStage.getExstage();
		
	}
}
