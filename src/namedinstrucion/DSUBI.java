package namedinstrucion;

import instructiontype.ItypeInstruction;

public class DSUBI extends ItypeInstruction {
	public DSUBI(String rt, String rs, int imm) {
		this.rt = rt;
		this.rs = rs;
		this.imm = imm;
	}
}
