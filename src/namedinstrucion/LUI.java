package namedinstrucion;

import instructiontype.ItypeInstruction;

public class LUI extends ItypeInstruction {
	public LUI(String rt, String rs, int imm) {
		this.rt = rt;
		this.rs = rs;
		this.imm = imm;
	}
}
