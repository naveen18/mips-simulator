package namedinstrucion;

import java.util.ArrayList;

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
		this.result = val2 - this.imm;		
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
		return l;	
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
