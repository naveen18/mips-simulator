package namedinstrucion;

import java.util.ArrayList;

import common.Instruction;

public class HLT extends Instruction{

	public HLT(String opcode, String pipelineType){
		super(opcode, pipelineType);
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
	public String getDestinationRegister() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getImmediate() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
