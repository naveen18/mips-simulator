package pipeline;

import java.util.ArrayList;


import common.AppConfig;
import common.CodeLoader;
import cache.InstructionCache;
import common.constants.CommonConstants;
import main.Main;

public class FetchStage {
	public boolean busy;
	static int fetchTime;
	
	public static void fetchInstruction(int instIndex) {
		if(instIndex > CodeLoader.programStore.size() - 1 && DecodeStage.branchAddress == -1){
			if(allStagesEmpty()){
				Pipeline.done = 1;
			}
			return;
		}
		if(instIndex == CodeLoader.programStore.size()){
			instIndex = DecodeStage.branchAddress;
		}
//		if(Pipeline.oneCycleDelay == true){
//			Pipeline.oneCycleDelay = false;
//			return;
//		}
		if(AppConfig.appConfig.isCacheOn){
			if (!InstructionCache.presentInCache(instIndex)) {
				if((IcacheProcess.IcacheProcessOn)){
					//System.out.println("Icache working at" + Main.clockCycle + " for " + instIndex);
					return;
				} else if(DcacheProcess.startedAt == Main.clockCycle ||  DcacheProcess.startedAt == -1 || DcacheProcess.startedAt  == Main.clockCycle - 1 && !Controller.firstWordMiss && Controller.secondWordMiss) {
					//System.out.println("Icache miss at " + Main.clockCycle);
					//System.out.println("Icache miss for "+  instIndex + " icach got bus at " + Main.clockCycle);
					DcacheProcess.DcacheProcessOn = false;
					IcacheProcess.IcacheProcessOn = true;
					IcacheProcess.address = instIndex;
					IcacheProcess.cycleCount = 3*AppConfig.appConfig.getBlockSizeInWords();
					Pipeline.owner = CommonConstants.ICACHE;
					InstructionCache.numIcacheMiss++;
					return;
				}
				else{
					//System.out.println("Icache miss for "+  instIndex + " but Dcache has bus at " + Main.clockCycle);
					//System.out.println(DcacheProcess.startedAt);
				}
				// since instruction is not in cache return
				return;
			}
		}
		
		if(IssueStage.busy){
			return;
		}
		
		if(!Pipeline.branchIssued){	
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
	
	public static boolean allStagesEmpty(){
		return IssueStage.issueStageQueue.isEmpty() && DecodeStage.decStageQueue.isEmpty()
				&& ExecuteStage.execStageQueue.isEmpty() && WriteBackStage.wbStageQueue.isEmpty();
	}

}
