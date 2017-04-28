package functionalunits;

public abstract class FunctionalUnit {
	public String opcode;
	public String reg1;
	public String reg2;
	public String reg3;
	public int executionTimeRequired=0;
	public int currExecutionTime = 0;
	public int scoreBoardRowId = 0;
}
