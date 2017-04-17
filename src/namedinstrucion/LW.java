package namedinstrucion;

import instructiontype.RtypeInstruction;

public class LW extends RtypeInstruction {
	int offset;
	public LW(String rd, String rs, int offset) {
		this.rd = rd;
		this.rs = rs;
		this.offset = offset;
	}
}
