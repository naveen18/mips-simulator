package common;

public abstract class Instruction {
	public String pipelineType;
	public String opcode;
	public double result;
	public int fetchedTimeStap = -1;
	public int decodeTimeStamp = -1;
	public int executionTimeStamp = -1;
	public int writeBackStamp = -1;
	
	public Instruction(String opcode, String pipelineType){
		this.opcode = opcode;
		this.pipelineType = pipelineType;
	}
	
	public abstract void execute();
	public abstract void write();
	public abstract void getRegisters();
	public abstract void getImmediate();
	
}
