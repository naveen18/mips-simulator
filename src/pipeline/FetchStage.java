package pipeline;

import test.Test;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import common.Instruction;

public class FetchStage {
	private static FetchStage fStage = null;
	private boolean busy;
	
	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public static FetchStage getFstage() {
		if (fStage == null)
			fStage = new FetchStage();
		return fStage;
	}
	
	public void fetchInstruction(Instruction inst){
		fStage.busy = true;
		DecodeStage decStage = DecodeStage.getDecStage();
		while(decStage.isBusy()); // lock
		decStage.decodeInstruction(inst);
		inst.setFetchedTimeStap(Test.clockCycle);
	}
	
	
	
}
