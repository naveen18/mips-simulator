package namedinstrucion;

import instructiontype.ItypeInstruction;
import registers.Register;

public class ANDI extends ItypeInstruction{
	public ANDI(String rt, String rs, int imm, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = rt;
		this.reg2 = rs;
		this.imm = imm;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		double val2 = Register.getRegister(this.reg2);
		this.result = val2 + this.imm;
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		Register.setRegister(this.reg1, this.result);
		
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
