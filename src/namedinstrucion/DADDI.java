package namedinstrucion;

import instructiontype.ItypeInstruction;

public class DADDI extends ItypeInstruction {
	public DADDI(String rt, String rs, int imm) {
		this.reg1 = rt;
		this.reg2 = rs;
		this.imm = imm;
	}
}
