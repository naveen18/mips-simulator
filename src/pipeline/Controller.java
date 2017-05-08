package pipeline;

import cache.DataCache;
import common.Instruction;
import common.constants.CommonConstants;
import main.Main;
import util.Utilities;

public class Controller {	

	public static boolean iCacheHappended = false;
	public static boolean firstWordMiss = false;
	public static boolean secondWordMiss = false;
	
	public static boolean controlLoadDouble(Instruction inst, int scbdrowId) throws Exception{
		int address = Utilities.getAddressOfLoad(inst);
		if(DataCache.isHit(address) && DataCache.isHit(address + 4)){
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn){
			return false;
		}
		else if(IcacheProcess.IcacheProcessOn){
			iCacheHappended = true;
			return false;
		}
		else {
			if(!DataCache.isHit(address)) {
				firstWordMiss = true;
				DcacheProcess.DcacheProcessOn = true;
				Pipeline.owner = CommonConstants.DCACHE;
				DcacheProcess.startedAt = Main.clockCycle;
				DcacheProcess.address = address;
				if(DataCache.isEvictionRequired(address)){
					DcacheProcess.cycleCount = 24;
				} else {
					DcacheProcess.cycleCount = 12;
				}
			} else if(!DataCache.isHit(address + 4)){
				secondWordMiss = true;
				address = address + 4; // next word for double
				DcacheProcess.DcacheProcessOn = true;
				Pipeline.owner = CommonConstants.DCACHE;
				DcacheProcess.startedAt = Main.clockCycle;
				DcacheProcess.address = address;
				if(DataCache.isEvictionRequired(address)){
					DcacheProcess.cycleCount = 24;
				} else {
					DcacheProcess.cycleCount = 12;
				}
			}
		}
		
		return false;
	}
	
	public static boolean controlLoadWord(Instruction inst, int scbdrowId) throws Exception{
		int address = Utilities.getAddressOfLoad(inst);
		if(DataCache.isHit(address)){
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn || IcacheProcess.IcacheProcessOn){
			return false;
		} else {
			DcacheProcess.DcacheProcessOn = true;
			Pipeline.owner = CommonConstants.DCACHE;
			DcacheProcess.startedAt = Main.clockCycle;
			DcacheProcess.address = address;
			if(DataCache.isEvictionRequired(address)){
				DcacheProcess.cycleCount = 24;
			} else {
				DcacheProcess.cycleCount = 12;
			}
		}
		return false;
	}
	
	public static boolean controlStoreDouble(Instruction inst, int scbdrowId) throws Exception{
		int address = Utilities.getAddressOfStore(inst);
		if(DataCache.isHit(address) && DataCache.isHit(address + 4)){
			DataCache.setDirtyForAddress(address);
			DataCache.setDirtyForAddress(address + 4);
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn){
			return false;
		}
		else if(IcacheProcess.IcacheProcessOn){
			iCacheHappended = true;
			return false;
		}
		else {
			if(!DataCache.isHit(address)) {
				firstWordMiss = true;
				DcacheProcess.DcacheProcessOn = true;
				Pipeline.owner = CommonConstants.DCACHE;
				DcacheProcess.startedAt = Main.clockCycle;
				DcacheProcess.address = address;
				if(DataCache.isEvictionRequired(address)){
					DcacheProcess.cycleCount = 24;
				} else {
					DcacheProcess.cycleCount = 12;
				}
			} else if(!DataCache.isHit(address + 4)){
				secondWordMiss = true;
				address = address + 4; // next word for double
				DcacheProcess.DcacheProcessOn = true;
				Pipeline.owner = CommonConstants.DCACHE;
				DcacheProcess.startedAt = Main.clockCycle;
				DcacheProcess.address = address;
				if(DataCache.isEvictionRequired(address)){
					DcacheProcess.cycleCount = 24;
				} else {
					DcacheProcess.cycleCount = 12;
				}
			}
		}
		return false;
	}
	
	public static boolean controlStoreWord(Instruction inst, int scbdrowId) throws Exception{
		int address = Utilities.getAddressOfStore(inst);
		if(DataCache.isHit(address)){
			DataCache.setDirtyForAddress(address);
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn || IcacheProcess.IcacheProcessOn){
			return false;
		} else {
			DcacheProcess.DcacheProcessOn = true;
			Pipeline.owner = CommonConstants.DCACHE;
			DcacheProcess.startedAt = Main.clockCycle;
			DcacheProcess.address = address;
			if(DataCache.isEvictionRequired(address)){
				DcacheProcess.cycleCount = 24;
			} else {
				DcacheProcess.cycleCount = 12;
			}
		}
		return false;
	}
}
