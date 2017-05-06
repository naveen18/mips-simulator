package cache;

import common.AppConfig;
import util.Utilities;

public class InstructionCache {
	// initialize parameters based on config
	static int numBlocks = AppConfig.appConfig.getNumCacheBlock();
	static int blockSizeinWords = AppConfig.appConfig.getBlockSizeInWords();
	static int[][] cache = new int[numBlocks][blockSizeinWords];
	public static int numIcacheRequests = 0;
	public static int numIcacheMiss = 0;
	public static int numIcacheHits = 0;

	public static void initCache() {
		for (int i = 0; i < numBlocks; i++) { // init cache with -1
			for (int j = 0; j < blockSizeinWords; j++) {
				cache[i][j] = -1;
			}
		}
	}

	public static boolean presentInCache(int address) { // check if the instruction is present in cache
														// with the help of index (using instruction number as instruction address)
		numIcacheRequests++;
		for (int i = 0; i < numBlocks; i++) {
			for (int j = 0; j < blockSizeinWords; j++) {
				if (cache[i][j] == address){
					return true;
				}
			}
		}
		putInCache(address);  // get from main memory if cache miss
		return false;
	}

	private static void putInCache(int address) { // gets a complete block in cache
		int offsetMask = Utilities.getMask(blockSizeinWords);
		int blockMask = Utilities.getMask(numBlocks);
		int offset  = address & offsetMask;
		//remove the bits from address that were used to calculate offset
		int shiftedAddr = address>>(int)(Math.log(blockSizeinWords)/Math.log(2));
		int blockadd = blockMask & shiftedAddr;
		for (int j = 0; j < blockSizeinWords; j++) {
			int index = j - offset + address;
			cache[blockadd][j] = index;
		}
	}
	
	public static void printInstructionCache(){ // method to print cache for debugging
		for (int i = 0; i < numBlocks; i++) {
			for (int j = 0; j < blockSizeinWords; j++) {
				System.out.println(cache[i][j] + " ");
			}
			System.out.println();
		}
	}

}
