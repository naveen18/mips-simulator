package namedinstrucion;

import instructiontype.ItypeInstruction;
import registers.Register;

public class DSUBI extends ItypeInstruction {
	public DSUBI(String reg1, String reg2, int imm, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.imm = imm;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		double val2 = Register.getRegister(this.reg2);
		double val1 = val2 - this.imm;
		Register.setRegister(this.reg1, val1);
		
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
