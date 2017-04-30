package namedinstrucion;

import java.util.ArrayList;

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
	public void execute() throws Exception {
		// TODO Auto-generated method stub
		double val2 = Register.getRegister(this.reg2);
		this.result = val2 + this.imm;
		
	}

	@Override
	public void write() throws Exception {
		// TODO Auto-generated method stub
		Register.setRegister(this.reg1, this.result);
		
	}

	@Override
	public ArrayList<String> getSourceRegisters() {
		ArrayList<String> l = new ArrayList<>();
		l.add(this.reg2);
		return l;
		// TODO Auto-generated method stub
	}

	@Override
	public Integer getImmediate() {
		return this.imm;
		// TODO Auto-generated method stub	
	}
	
	@Override
	public String getDestinationRegister() {
		return this.reg1;
		// TODO Auto-generated method stub
	}
	
}
