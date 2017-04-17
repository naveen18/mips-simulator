package namedinstrucion;

import instructiontype.RtypeInstruction;

public class SW extends RtypeInstruction {
	public SW(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
