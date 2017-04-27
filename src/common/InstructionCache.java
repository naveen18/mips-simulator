package common;

public class InstructionCache {
	// initialize parameters based on config
	static int numBlocks = AppConfig.appConfig.getNumCacheBlock();
	static int blockSizeinWords = AppConfig.appConfig.getBlockSizeInWords();
	static int[][] cache = new int[numBlocks][blockSizeinWords];
	static int currentCachePointer = 0;

	public static void initCache() {
		for (int i = 0; i < numBlocks; i++) { // init cache with -1
			for (int j = 0; j < blockSizeinWords; j++) {
				cache[i][j] = -1;
			}
		}
	}

	public static boolean presentInCache(int address) { // check if the instruction is present in cache
														// with the help of index
		for (int i = 0; i < numBlocks; i++) {
			for (int j = 0; j < blockSizeinWords; j++) {
				if (cache[i][j] == address)
					return true;
			}
		}
		putInCache(address);  // get from main memory if cache miss
		return false;
	}

	private static void putInCache(int address) { // gets a complete block in cache
		for (int j = 0; j < blockSizeinWords; j++) {
			cache[currentCachePointer][j] = address + j;
		}
		currentCachePointer = (currentCachePointer + 1) % numBlocks; //evict in LRU fashion
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
