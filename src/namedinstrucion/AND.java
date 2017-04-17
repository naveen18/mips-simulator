package namedinstrucion;

import instructiontype.RtypeInstruction;

public class AND extends RtypeInstruction{
	public AND (String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
