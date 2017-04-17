package namedinstrucion;

import instructiontype.RtypeInstruction;

public class DADD extends RtypeInstruction{
	public DADD(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
