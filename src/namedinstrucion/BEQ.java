package namedinstrucion;

import instructiontype.RtypeInstruction;

public class BEQ extends RtypeInstruction{
	public BEQ(String rd, String rs, String rt){
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
