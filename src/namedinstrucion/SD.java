package namedinstrucion;

import instructiontype.RtypeInstruction;

public class SD extends RtypeInstruction{
	public SD(String reg1, String reg2, int offset) {
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.offset = offset;
	}
}
