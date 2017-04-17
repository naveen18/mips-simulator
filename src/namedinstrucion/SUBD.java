package namedinstrucion;

import instructiontype.RtypeInstruction;

public class SUBD extends RtypeInstruction{
	public SUBD(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
