package namedinstrucion;

import instructiontype.ItypeInstruction;

public class ANDI extends ItypeInstruction{
	public ANDI(String rt, String rs, int imm) {
		this.reg1 = rt;
		this.reg2 = rs;
		this.imm = imm;
	}
}
