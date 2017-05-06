package cache;

import java.util.HashMap;

import common.Memory;

public class DataCache {

	public static DataCacheSet[] dcache = new DataCacheSet[2];
	public static HashMap<Integer, Integer> addrToVal = new HashMap<>();
	public static int numDcacheRequests = 0;
	public static int numDcacheMiss = 0;
	public static int numDcacheHits = 0;
	
	public static void initDataCache(){
		dcache[0] = new DataCacheSet();
		dcache[1] = new DataCacheSet();
		dcache[0].blocks[0] = new DataCacheBlock();
		dcache[0].blocks[1] = new DataCacheBlock();
		dcache[1].blocks[0] = new DataCacheBlock();
		dcache[1].blocks[1] = new DataCacheBlock();
	}

	public static boolean isHit(int address) {
		//System.out.println("call for address " + address);
		int set  = getSetforAddress(address);
		for (int i = 0; i < 2; i++) { // check if the address lies in baseAddress of block and baseAddress + 15
			for(int j=0; j<4; j++){
				if (dcache[set].blocks[i].words[j] == address) {
					return true;
				}
			}
		}
		return false;
	}

	public static void putInCache(int address) throws Exception {
		int set = getSetforAddress(address);
		int candidate = dcache[set].evictionCandidate;
		boolean dirty = isCandidateDirty(candidate, set);
		if (dirty) { // if block dirty the  write in memory
			writeBlockToMemory(candidate, set);
		}
		
		int baseAddress = getbaseAddressforAddress(address);
		dcache[set].blocks[candidate].baseAddress = baseAddress;
		for (int i=0; i<4; i++){ // get block from memory
			dcache[set].blocks[candidate].words[i] = baseAddress + 4*i;
			addrToVal.put(baseAddress + 4*i, Memory.getWord(baseAddress + 4*i));
		}
		dcache[set].evictionCandidate = 1 - candidate; // LRU update
	}

	public static boolean isCandidateDirty(int blockNum, int set) {
		if (dcache[set].blocks[blockNum].isDirty)
			return true;
		return false;
	}

	public static void writeBlockToMemory(int blockNum, int set) {
		for (int i = 0; i < 4; i++) {
			int address = dcache[set].blocks[blockNum].words[i];
			Memory.storeWord(address, addrToVal.get(address));
			addrToVal.remove(address);
		}
		dcache[set].blocks[blockNum].isDirty = false;
	}
	
	public static void writeInCache(int address, int val) throws Exception {
		if(!isHit(address)) {
			System.out.println("MISS\n");
			putInCache(address);
		}
		int set = getSetforAddress(address);
		int blockNum = getBlockNumber(address);
		int offset = getOffsetforAddress(address);
		System.out.println("writing at " + address);
		System.out.println(offset);
		dcache[set].blocks[blockNum].words[offset] = address;
		addrToVal.put(address, val);
		dcache[set].evictionCandidate = 1 - blockNum;
		dcache[set].blocks[blockNum].isDirty = true;
	}
	
	public static int getBlockNumber(int address) {
		int set = getSetforAddress(address);
		for (int i = 0; i < 2; i++) { // check if the address lies in baseAddress of block and baseAddress + 15
			for(int j=0; j<4; j++){
				if (dcache[set].blocks[i].words[j] == address) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public static int getSetforAddress(int address){
		address = address>>2; // last two bits are always zero thus gives wrong set
		int set = address & 0b100;
		set = set>>2;
		return set;
	}
	
	public static int getOffsetforAddress(int address){
		int offset = address & 0b1100;
		offset = offset>>2;
		return offset;
	}
	
	public static int getbaseAddressforAddress(int address){ // makes last 4 bits as 0
		return (address>>4)<<4;
	}
	
	public static boolean isEvictionRequired (int address) {
		int set = getSetforAddress(address);
		int evicCandidate = dcache[set].evictionCandidate;
		if(dcache[set].blocks[evicCandidate].isDirty){
			return true;
		}
		return false;
	}
	
	public static void printDcache() {
		for (int i = 0; i < 2; i++) {
			System.out.println("Set " + i);
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					System.out.println("address " + dcache[i].blocks[j].words[k] + " value " + addrToVal.get(dcache[i].blocks[j].words[k]));
				}
				System.out.println();
			}
		}
	}
	
	public static void setDirtyForAddress(int address){
		int set = getSetforAddress(address);
		for (int i = 0; i < 2; i++) { // check if the address lies in baseAddress of block and baseAddress + 15
			for(int j=0; j<4; j++){
				if (dcache[set].blocks[i].words[j] == address) {
					dcache[set].blocks[i].isDirty = true;
				}
			}
		}
	}
	
	
}