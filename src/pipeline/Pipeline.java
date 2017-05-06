package pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.AppConfig;
import main.Main;

public class Pipeline {
	public static HashMap<Integer, Integer> scobdIdtoInstId = new HashMap<>();
	public static ArrayList<List<Integer>> scoreboard = new ArrayList<List<Integer>>(); 
	public static int scoreboardRowId = 0;
	public static int instIndex;
	public static int done = 0;
	public static boolean oneCycleDelay = false;
	public static boolean halted = false;
	public static String owner = null;
	public static void startPipeLine() throws Exception {
		while(done!=1) {
			Main.clockCycle++;
			if(AppConfig.appConfig.isCacheOn){
				if(DcacheProcess.DcacheProcessOn){
					DcacheProcess.run();
				}
				else if(IcacheProcess.IcacheProcessOn){
					IcacheProcess.run();
				}
			}
			WriteBackStage.writebackInstruction();
			ExecuteStage.executeInstruction();
			DecodeStage.decodeInstruction();
			IssueStage.issueInstruction();
			FetchStage.fetchInstruction(Pipeline.instIndex);
			if(Main.clockCycle > 2000){
				//System.out.println("FED UP");
				break;
			}
			//PrintMethods.printScoreBoard();
		}
		
	}
	
}
