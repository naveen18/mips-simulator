package common;

public class Instruction {
	private int fetchedTimeStap = -1;
	private int decodeTimeStamp = -1;
	private int executionTimeStamp = -1;
	private int writeBackStamp = -1;
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
