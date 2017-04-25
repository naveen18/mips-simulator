package pipeline;

import common.Instruction;
import test.Test;
import pipeline.FetchStage;

public class WriteBackStage {
	public int id;
	
	public WriteBackStage(int id){
		this.id  = id;
	}
	
	public void writebackInstruction(Instruction inst, IssueStage is){
		// perform task
		FetchStage.isMap.get(this.id).busy = false;
		System.out.println(Test.clockCycle);
	}
}
