package pipeline;

import cache.InstructionCache;
import common.constants.CommonConstants;

public class IcacheProcess {
	static int cycleCount = -1;
	static int address;
	static boolean IcacheProcessOn = false;
	
	public static void run(){
		if(IcacheProcessOn && CommonConstants.ICACHE.equals(Pipeline.owner)){
			cycleCount--;
		}
		if(cycleCount == 0){
			InstructionCache.putInCache(address);
			IcacheProcessOn = false;
			cycleCount = -1;
		}
	}
}
