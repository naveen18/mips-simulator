package pipeline;

import cache.DataCache;
import common.Instruction;
import common.constants.CommonConstants;
import main.Main;
import util.Utilities;

public class Controller {	
	public static boolean controlLoadDouble(Instruction inst, int scbdrowId) throws Exception{
		int address = Utilities.getAddressOfLoad(inst);
		if(DataCache.isHit(address) && DataCache.isHit(address + 4)){
			//DataCache.printDcache();
			//System.out.println( " hit for " + inst.opcode + " " + inst.getDestinationRegister() + " " + inst.getSourceRegisters() + " " +inst.getImmediate());
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn || IcacheProcess.IcacheProcessOn){
			return false;
		}
		else {
			if(!DataCache.isHit(address)) {
//				System.out.println("call for address" + address + " at " + Main.clockCycle);
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
				address = address + 4; // next word for double
//				System.out.println("call for address" + address + " at " + Main.clockCycle);
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
//		System.out.println(scbdrowId + " need " + address);
		if(DataCache.isHit(address)){
			//DataCache.printDcache();
			//System.out.println( " hit for " + inst.opcode + " " + inst.getDestinationRegister() + " " + inst.getSourceRegisters() + " " +inst.getImmediate());
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn || IcacheProcess.IcacheProcessOn){
			return false;
		} else {
//			System.out.println("call for address" + address + " at " + Main.clockCycle);
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
//		System.out.println(scbdrowId + " need " + address);
		if(DataCache.isHit(address) && DataCache.isHit(address + 4)){
			//store is going to write at address and address + 4 hence set dirty bits for them in cache
			DataCache.setDirtyForAddress(address);
			DataCache.setDirtyForAddress(address + 4);
			//DataCache.printDcache();
			//System.out.println( " hit for " + inst.opcode + " " + inst.getDestinationRegister() + " " + inst.getSourceRegisters() + " " +inst.getImmediate());
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn || IcacheProcess.IcacheProcessOn){
			return false;
		}
		else {
			if(!DataCache.isHit(address)) {
//				System.out.println("call for address" + address + " at " + Main.clockCycle);
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
				address = address + 4; // next word for double
//				System.out.println("call for address" + address + " at " + Main.clockCycle);
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
//		System.out.println(scbdrowId + " need " + address);
		if(DataCache.isHit(address)){
			DataCache.setDirtyForAddress(address);
			//DataCache.printDcache();
			//System.out.println( " hit for " + inst.opcode + " " + inst.getDestinationRegister() + " " + inst.getSourceRegisters() + " " +inst.getImmediate());
			return true;
		}
		else if(DcacheProcess.DcacheProcessOn || IcacheProcess.IcacheProcessOn){
			return false;
		} else {
//			System.out.println("call for address" + address + " at " + Main.clockCycle);
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
