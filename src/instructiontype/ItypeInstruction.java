package instructiontype;

import common.Instruction;

public class ItypeInstruction extends Instruction {
	protected String op;
	protected String rt;
	protected String rs;
	protected int imm;
	
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getRs() {
		return rs;
	}
	public void setRs(String rs) {
		this.rs = rs;
	}

	public int getImmediate() {
		return imm;
	}
	public void setImmediate(int immediate) {
		this.imm = immediate;
	}

	@Override
	public String toString() {
		return "JtypeInstruction [op=" + op + ", rs=" + rs + ", immediate=" + imm + "]";
	}
	
}
