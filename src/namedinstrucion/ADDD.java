package namedinstrucion;

import instructiontype.RtypeInstruction;
import registers.Register;

public class ADDD extends RtypeInstruction{
	public ADDD(String rd, String rs, String rt, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = rd;
		this.reg2 = rs;
		this.reg3 = rt;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		double val2 = Register.getRegister(this.reg2);
		double val3 = Register.getRegister(this.reg3);
		double val1 = val2 + val3;
		Register.setRegister(this.reg1,val1);
		
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
