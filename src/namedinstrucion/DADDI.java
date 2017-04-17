package namedinstrucion;

import instructiontype.ItypeInstruction;

public class DADDI extends ItypeInstruction {
	public DADDI(String rt, String rs, int imm) {
		this.rt = rt;
		this.rs = rs;
		this.imm = imm;
	}
}
