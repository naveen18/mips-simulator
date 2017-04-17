package namedinstrucion;

import instructiontype.RtypeInstruction;

public class ADDD extends RtypeInstruction{
	public ADDD(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
