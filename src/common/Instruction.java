package common;

import java.util.ArrayList;

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
	
	public abstract void execute() throws Exception;
	public abstract void write() throws Exception;
	public abstract ArrayList<String> getSourceRegisters();
	public abstract Integer getImmediate();
	public abstract String getDestinationRegister();
	
}
