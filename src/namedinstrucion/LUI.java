package namedinstrucion;

import instructiontype.ItypeInstruction;

public class LUI extends ItypeInstruction {
	public LUI(String reg1, int imm) {
		this.reg1 = reg1;
		this.imm = imm;
	}
}
