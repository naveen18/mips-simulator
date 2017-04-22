package namedinstrucion;

import instructiontype.RtypeInstruction;

public class DADD extends RtypeInstruction {
	public DADD(String reg1, String reg2, String reg3) {
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.reg3 = reg3;
	}
}
