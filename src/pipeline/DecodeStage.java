package pipeline;

import java.util.LinkedList;
import java.util.Queue;

import common.Instruction;
import test.Test;

public class DecodeStage {
	public static Queue<Integer>  decStageQueue = new LinkedList<Integer>();
	private boolean busy;

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public static void decodeInstruction(){
		if(decStageQueue.isEmpty())
			return;
		int instIndex  = decStageQueue.poll();
//		System.out.println("decode stage completed");
		ExecuteStage.execStageQueue.offer(instIndex);
	}
}
