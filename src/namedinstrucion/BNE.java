package namedinstrucion;

import instructiontype.RtypeInstruction;

public class BNE extends RtypeInstruction{
	public BNE(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
