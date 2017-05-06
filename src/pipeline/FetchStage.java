package pipeline;

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
import cache.InstructionCache;
import common.constants.CommonConstants;
import main.Main;

public class FetchStage {
	public boolean busy;
	static int fetchTime;
	
	public static void fetchInstruction(int instIndex) {
		if(instIndex > CodeLoader.programStore.size() - 1 && DecodeStage.branchAddress == -1)
			return;
		if(instIndex == CodeLoader.programStore.size()){
			instIndex = DecodeStage.branchAddress;
		}
		if(Pipeline.oneCycleDelay == true){
			Pipeline.oneCycleDelay = false;
			return;
		}	
		if(AppConfig.appConfig.isCacheOn){
			if (!InstructionCache.presentInCache(instIndex)) {
				if((IcacheProcess.IcacheProcessOn)){
					return;
				} else if(DcacheProcess.startedAt == Main.clockCycle ||  DcacheProcess.startedAt == -1){
					DcacheProcess.DcacheProcessOn = false;
					IcacheProcess.IcacheProcessOn = true;
					IcacheProcess.address = instIndex;
					IcacheProcess.cycleCount = 12;
					Pipeline.owner = CommonConstants.ICACHE;
					InstructionCache.numIcacheMiss++;
					return;
				} else {
					//System.out.println("Exceptional case for " + instIndex);
				}
			}
		}
		
		if(IssueStage.busy){
			return;
		}
			
		//System.out.println("fetching " + instIndex + " at " + Main.clockCycle);
		Pipeline.scoreboard.add(Pipeline.scoreboardRowId, new ArrayList<Integer>());
		// filling 8 dummy integers
		for(int i=0; i<8; i++) Pipeline.scoreboard.get(Pipeline.scoreboardRowId).add(0);
		Pipeline.scobdIdtoInstId.put(Pipeline.scoreboardRowId, instIndex);
		IssueStage.issueStageQueue.offer(Pipeline.scoreboardRowId);
		IssueStage.busy=true;
		Pipeline.scoreboard.get(Pipeline.scoreboardRowId).set(CommonConstants.FETCH_COLUMN, Main.clockCycle);
		Pipeline.scoreboardRowId++;
		//System.out.println(instIndex);
		Pipeline.instIndex++;
		
		if(DecodeStage.branchAddress != -1){ // need to branch at branchAddress in next clock cycle
			Pipeline.instIndex = DecodeStage.branchAddress;
			DecodeStage.branchAddress = -1;
			return;
		}
	}

}
