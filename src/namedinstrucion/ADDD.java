package namedinstrucion;

import instructiontype.RtypeInstruction;

public class ADDD extends RtypeInstruction{
	public ADDD(String rd, String rs, String rt) {
		this.reg1 = rd;
		this.reg2 = rs;
		this.reg3 = rt;
	}
}
