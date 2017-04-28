package namedinstrucion;

import java.util.ArrayList;

import instructiontype.RtypeInstruction;

public class BNE extends RtypeInstruction{
	public BNE(String reg1, String reg2, String targetLabel, String opcode, String pipelineType) {
		super(opcode, pipelineType);
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.targetLabel = targetLabel; // beq and bne have target label in reg3
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
	public ArrayList<String> getSourceRegisters() {
		// TODO Auto-generated method stub
		ArrayList<String> l = new ArrayList<>();
		l.add(this.reg1);
		l.add(this.reg2);
		return l;	
	}

	@Override
	public String getDestinationRegister() {
		// TODO Auto-generated method stub
		return this.targetLabel;
	}

	@Override
	public Integer getImmediate() {
		// TODO Auto-generated method stub
		return null;
	}
}
