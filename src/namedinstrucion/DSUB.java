package namedinstrucion;

import instructiontype.RtypeInstruction;

public class DSUB extends RtypeInstruction{
	public DSUB(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
