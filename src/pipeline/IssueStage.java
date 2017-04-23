package pipeline;

import java.util.Map.Entry;

import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;
import namedinstrucion.LI;

public class IssueStage {
	public int id;
	public boolean busy;
	public void issueInstruction(Instruction inst, int id) {
		this.busy = true;
		DecodeStage dec = new DecodeStage(id);
		dec.decodeInstruction(inst);
	}
	
	public IssueStage(int id){
		this.id = id;
	}
	public String getType(Instruction inst){
		if((LI) inst instanceof LI) {
			
		}
		return null;
	}
}
