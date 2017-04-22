package namedinstrucion;

import instructiontype.ItypeInstruction;

public class DSUBI extends ItypeInstruction {
	public DSUBI(String reg1, String reg2, int imm) {
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.imm = imm;
	}
}
