package instructiontype;

import common.Instruction;

public abstract class RtypeInstruction extends Instruction {
	public RtypeInstruction(String opcode, String pipelineType) {
		super(opcode, pipelineType);
	}
	public String reg1;
	public String reg2;
	public String reg3;
	public int offset;
	public int imm;
	String shamt;
	String funct;
}
