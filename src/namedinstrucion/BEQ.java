package namedinstrucion;

import java.util.ArrayList;

import instructiontype.RtypeInstruction;

public class BEQ extends RtypeInstruction{
	public BEQ(String reg1, String reg2, String targetlabel, String opcode, String pipelineType){
		super(opcode, pipelineType);
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.targetLabel = targetlabel; // 3rd argument in bne and beq is target label
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
