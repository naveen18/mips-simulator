package instructiontype;

import common.Instruction;

public abstract class ItypeInstruction extends Instruction {
	public ItypeInstruction(String opcode, String pipelineType) {
		super(opcode, pipelineType);
	}
	public String op;
	public String reg1;
	public String reg2;
	public int imm;	
}
