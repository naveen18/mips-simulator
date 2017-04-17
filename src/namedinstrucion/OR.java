package namedinstrucion;

import instructiontype.RtypeInstruction;

public class OR extends RtypeInstruction{
	public OR(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
