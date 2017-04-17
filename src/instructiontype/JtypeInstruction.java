package instructiontype;

import common.Instruction;

public class JtypeInstruction extends Instruction {
	String op;
	protected String targetAddress;
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getTargetAddress() {
		return targetAddress;
	}
	public void setTargetAddress(String targetAddress) {
		this.targetAddress = targetAddress;
	}
	@Override
	public String toString() {
		return "ItypeInstruction [op=" + op + ", targetAddress=" + targetAddress + "]";
	}
	
}
