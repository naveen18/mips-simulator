package instructiontype;

import common.Instruction;

public class RtypeInstruction extends Instruction {
	String op;
	protected String rs;
	protected String rt;
	protected String rd;
	String shamt;
	String funct;
	
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
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}
	public String getRd() {
		return rd;
	}
	public void setRd(String rd) {
		this.rd = rd;
	}
	public String getShamt() {
		return shamt;
	}
	public void setShamt(String shamt) {
		this.shamt = shamt;
	}
	public String getFunct() {
		return funct;
	}
	public void setFunct(String funct) {
		this.funct = funct;
	}
	
	@Override
	public String toString() {
		return "RtypeInstruction [op=" + op + ", rs=" + rs + ", rt=" + rt + ", rd=" + rd + ", shamt=" + shamt
				+ ", funct=" + funct + "]";
	}
}
