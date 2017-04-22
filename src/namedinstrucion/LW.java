package namedinstrucion;

import instructiontype.RtypeInstruction;

public class LW extends RtypeInstruction {
	int offset;
	public LW(String reg1, String reg2, int offset) {
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.offset = offset;
	}
}
