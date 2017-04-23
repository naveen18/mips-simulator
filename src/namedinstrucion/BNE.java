package namedinstrucion;

import instructiontype.RtypeInstruction;

public class BNE extends RtypeInstruction{
	public BNE(String rd, String rs, String rt, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = rd;
		this.reg2 = rs;
		this.reg3 = rt;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getRegisters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getImmediate() {
		// TODO Auto-generated method stub
		
	}
}
