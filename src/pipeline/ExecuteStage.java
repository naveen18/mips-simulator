package pipeline;

import common.Instruction;

public class ExecuteStage {
	int id;
	public ExecuteStage(int id){
		this.id = id;
	}
	
	public void executeInstruction(Instruction inst) {
		WriteBackStage wbstage = new WriteBackStage(this.id);
		wbstage.writebackInstruction(inst);
	}
}
