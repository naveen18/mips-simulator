package pipeline;

import test.Test;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.InstructionCache;
import common.constants.CommonConstants;

public class FetchStage {
	public boolean busy;
	static int fetchTime;
	
	public static void fetchInstruction(int instIndex) {
		if(!InstructionCache.presentInCache(instIndex))
			fetchTime = Test.clockCycle + AppConfig.appConfig.getBlockSizeInWords()*CommonConstants.cacheMissPenalty;
		if(Test.clockCycle < fetchTime)
			return;
//		System.out.println(instIndex);
		IssueStage.issueStageQueue.offer(instIndex);
		Pipeline.instIndex++;
	}

}
