package common;

import java.util.Arrays;

public class Instruction {
	private int fetchedTimeStap = -1;
	private int decodeTimeStamp = -1;
	private int executionTimeStamp = -1;
	private int writeBackStamp = -1;
	
	static int count = 0;
	
	private int instructionNum;
	private String opcode;
	private String[] operands;
	private String label;
	private String type;
	private String shiftAmt;
	private String funct;
	
	public Instruction(int instructionNum, String opcode, String[] operands, String label, String type, String shiftAmt,
			String funct) {
		super();
		this.instructionNum = instructionNum;
		this.opcode = opcode;
		this.operands = operands;
		this.label = label;
		this.type = type;
		this.shiftAmt = shiftAmt;
		this.funct = funct;
	}
	
	public Instruction() {
		// TODO Auto-generated constructor stub
		count++;
		this.instructionNum = count;
	}

	@Override
	public String toString() {
		return "Instruction [instructionNum=" + instructionNum + ", opcode=" + opcode + ", operands="
				+ Arrays.toString(operands) + ", label=" + label + ", type=" + type + ", shiftAmt=" + shiftAmt
				+ ", funct=" + funct + "]";
	}

	public int getInstructionNum() {
		return instructionNum;
	}
	public void setInstructionNum(int instructionNum) {
		this.instructionNum = instructionNum;
	}
	public String getOpcode() {
		return opcode;
	}
	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}
	public String[] getOperands() {
		return operands;
	}
	public void setOperands(String[] operands) {
		this.operands = operands;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShiftAmt() {
		return shiftAmt;
	}
	public void setShiftAmt(String shiftAmt) {
		this.shiftAmt = shiftAmt;
	}
	public String getFunct() {
		return funct;
	}
	public void setFunct(String funct) {
		this.funct = funct;
	}

	public int getFetchedTimeStap() {
		return fetchedTimeStap;
	}

	public void setFetchedTimeStap(int fetchedTimeStap) {
		this.fetchedTimeStap = fetchedTimeStap;
	}

	public int getDecodeTimeStamp() {
		return decodeTimeStamp;
	}

	public void setDecodeTimeStamp(int decodeTimeStamp) {
		this.decodeTimeStamp = decodeTimeStamp;
	}

	public int getExecutionTimeStamp() {
		return executionTimeStamp;
	}

	public void setExecutionTimeStamp(int executionTimeStamp) {
		this.executionTimeStamp = executionTimeStamp;
	}

	public int getWriteBackStamp() {
		return writeBackStamp;
	}

	public void setWriteBackStamp(int writeBackStamp) {
		this.writeBackStamp = writeBackStamp;
	}
}


