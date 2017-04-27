package pipeline;

import java.util.LinkedList;
import java.util.Queue;

import common.Instruction;
import test.Test;
import pipeline.FetchStage;

public class WriteBackStage {
	public static Queue<Integer>  wbStageQueue = new LinkedList<Integer>();
	
	public static void writebackInstruction(){
		// perform task
		if(wbStageQueue.isEmpty())
			return;
		int instIndex = wbStageQueue.poll();
		System.out.println(instIndex + " is finished at " + Test.clockCycle + "\n");
	}
}
