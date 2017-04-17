package namedinstrucion;

import instructiontype.ItypeInstruction;

public class LI extends ItypeInstruction{
	public LI(String rt, String rs, int imm) {
		this.rt = rt;
		this.rs = rs;
		this.imm = imm;
	}
}
