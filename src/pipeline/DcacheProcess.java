package pipeline;

import cache.DataCache;
import common.Instruction;
import common.constants.CommonConstants;

public class DcacheProcess {
	static int cycleCount = -1;
	static int address;
	static boolean DcacheProcessOn = false;
	static int startedAt = -1;
	
	public static void run() throws Exception{
		if(DcacheProcessOn && CommonConstants.DCACHE.equals(Pipeline.owner)){
			cycleCount--;
		}
		if(cycleCount == 0){
			DataCache.numDcacheMiss++;
			DataCache.putInCache(address);
			DcacheProcessOn = false;
			cycleCount = -1;
			DcacheProcess.startedAt = -1;
		}
	}
	
	public static void countDcacheRequests(Instruction inst) {
		if (inst.opcode.equals(CommonConstants.LD) || inst.opcode.equals(CommonConstants.SD)) {
			DataCache.numDcacheRequests = DataCache.numDcacheRequests + 2 ;
		} else if(inst.opcode.equals(CommonConstants.LW) || inst.opcode.equals(CommonConstants.SW)){
			DataCache.numDcacheRequests++;
		}
	}
}