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
		
		if(AppConfig.appConfig.isCacheOn){
			if (!InstructionCache.presentInCache(instIndex))
				fetchTime = Test.clockCycle + AppConfig.appConfig.getBlockSizeInWords() * CommonConstants.cacheMissPenalty;
			if (Test.clockCycle < fetchTime)
				return;
		}
		
		if(IssueStage.busy){
			return;
		}
		Pipeline.scoreboard.add(Pipeline.scoreboardRowId, new ArrayList<Integer>());
		// filling 8 dummy integers
		for(int i=0; i<8; i++) Pipeline.scoreboard.get(Pipeline.scoreboardRowId).add(0);
		Pipeline.scobdIdtoInstId.put(Pipeline.scoreboardRowId, instIndex);
		IssueStage.issueStageQueue.offer(Pipeline.scoreboardRowId);
		IssueStage.busy=true;
		Pipeline.scoreboard.get(Pipeline.scoreboardRowId).set(CommonConstants.FETCH_COLUMN, Test.clockCycle);
		Pipeline.scoreboardRowId++;
		Pipeline.instIndex++;
	}

}
