package namedinstrucion;

import java.util.ArrayList;

import common.Memory;
import instructiontype.ItypeInstruction;
import registers.Register;

public class LUI extends ItypeInstruction {
	public LUI(String reg1, int imm, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = reg1;
		this.imm = imm;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write() throws Exception {
		// TODO Auto-generated method stub
		int shiftedImmidiate = this.imm<<16;
		Register.setRegister(this.reg1, shiftedImmidiate);
	}

	@Override
	public ArrayList<String> getSourceRegisters() {
		// TODO Auto-generated method stub
		return null;	
	}

	@Override
	public Integer getImmediate() {
		// TODO Auto-generated method stub
		return this.imm;
	}

	@Override
	public String getDestinationRegister() {
		// TODO Auto-generated method stub
		return this.reg1;
	}
}
