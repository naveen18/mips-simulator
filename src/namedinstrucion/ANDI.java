package namedinstrucion;

import instructiontype.ItypeInstruction;

public class ANDI extends ItypeInstruction{
	public ANDI(String rt, String rs, int imm) {
		this.rt = rt;
		this.rs = rs;
		this.imm = imm;
	}
}
