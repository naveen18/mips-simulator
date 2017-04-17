package namedinstrucion;

import instructiontype.RtypeInstruction;

public class LD extends RtypeInstruction{
	public LD(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
