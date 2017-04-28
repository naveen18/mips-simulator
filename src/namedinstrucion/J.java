package namedinstrucion;

import java.util.ArrayList;

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
	public ArrayList<String> getSourceRegisters() {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public Integer getImmediate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDestinationRegister() {
		// TODO Auto-generated method stub
		return null;
	}

}
