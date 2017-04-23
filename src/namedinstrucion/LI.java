package namedinstrucion;

import common.Memory;
import instructiontype.ItypeInstruction;
import registers.Register;

public class LI extends ItypeInstruction{
	public LI(String reg1, int imm, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = reg1;
		this.imm = imm;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		Register.setRegister(this.reg1, this.imm);
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
