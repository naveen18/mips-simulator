package namedinstrucion;

import instructiontype.JtypeInstruction;

public class J extends JtypeInstruction{
	public J(String targetAddress, String opcode, String pipelineType){
		super(opcode, pipelineType);
		this.targetAddress = targetAddress;
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
	public void getRegisters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getImmediate() {
		// TODO Auto-generated method stub
		
	}
}
