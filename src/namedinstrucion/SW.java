package namedinstrucion;

import java.util.ArrayList;

import common.Memory;
import instructiontype.RtypeInstruction;
import registers.Register;

public class SW extends RtypeInstruction {
	public SW(String reg1, String reg2, int offset, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.offset = offset;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		Memory.storeWord((int)Register.getRegister(this.reg2) + offset, (int)Register.getRegister(this.reg1));
	}

	@Override
	public ArrayList<String> getSourceRegisters() {
		// TODO Auto-generated method stub
		ArrayList<String> l = new ArrayList<>();
		l.add(this.reg2);
		return l;
	}

	@Override
	public Integer getImmediate() {
		// TODO Auto-generated method stub
		return this.offset;
	}

	@Override
	public String getDestinationRegister() {
		// TODO Auto-generated method stub
		return this.reg1;
	}
}
