package namedinstrucion;

import instructiontype.ItypeInstruction;

public class ORI extends ItypeInstruction{
	public ORI(String reg1, String reg2, int imm) {
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.imm = imm;
	}
}
