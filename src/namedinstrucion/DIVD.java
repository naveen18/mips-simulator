package namedinstrucion;

import instructiontype.RtypeInstruction;

public class DIVD extends RtypeInstruction{
	public DIVD(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
