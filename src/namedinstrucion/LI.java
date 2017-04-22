package namedinstrucion;

import instructiontype.ItypeInstruction;

public class LI extends ItypeInstruction{
	public LI(String reg1, int imm) {
		this.reg1 = reg1;
		this.imm = imm;
	}
}
