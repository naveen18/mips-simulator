package instructiontype;

import common.Instruction;

public abstract class JtypeInstruction extends Instruction {
	
	public JtypeInstruction(String opcode, String pipelineType) {
		super(opcode, pipelineType);
	}

	protected String targetAddress;

	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}

	
}
