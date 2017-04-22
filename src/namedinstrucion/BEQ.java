package namedinstrucion;

import instructiontype.RtypeInstruction;

public class BEQ extends RtypeInstruction{
	public BEQ(String rd, String rs, String rt){
		this.reg1 = rd;
		this.reg2 = rs;
		this.reg3 = rt;
	}
}
