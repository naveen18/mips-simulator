package namedinstrucion;

import instructiontype.ItypeInstruction;

public class ORI extends ItypeInstruction{
	public ORI(String rt, String rs, int imm) {
		this.rt = rt;
		this.rs = rs;
		this.imm = imm;
	}
}
