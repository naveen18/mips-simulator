package instructiontype;

import common.Instruction;

public class RtypeInstruction extends Instruction {
	String op;
	public String reg1;
	public String reg2;
	public String reg3;
	public int offset;
	public int imm;
	String shamt;
	String funct;
}
