package namedinstrucion;

import instructiontype.RtypeInstruction;

public class MULTD extends RtypeInstruction{
	public MULTD(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
