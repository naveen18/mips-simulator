package namedinstrucion;

import instructiontype.RtypeInstruction;

public class SD extends RtypeInstruction{
	public SD(String rd, String rs, String rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
