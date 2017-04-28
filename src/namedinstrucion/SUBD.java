package namedinstrucion;

import java.util.ArrayList;

import instructiontype.RtypeInstruction;
import registers.Register;

public class SUBD extends RtypeInstruction{
	public SUBD(String reg1, String reg2, String reg3, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.reg3 = reg3;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		double val2 = Register.getRegister(this.reg2);
		double val3 = Register.getRegister(this.reg3);
		this.result = val2 - val3;
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		Register.setRegister(this.reg1, this.result);
	}

	@Override
	public ArrayList<String> getSourceRegisters() {
		// TODO Auto-generated method stub
		ArrayList<String> l = new ArrayList<>();
		l.add(this.reg2);
		l.add(this.reg3);
		return l;	
	}

	@Override
	public String getDestinationRegister() {
		// TODO Auto-generated method stub
		return this.reg1;
	}

	@Override
	public Integer getImmediate() {
		// TODO Auto-generated method stub
		return null;
	}
}
