package cache;

public class DataCacheBlock {
	boolean isDirty;
	int baseAddress;
	int value;
	int[] words;
	public DataCacheBlock(){
		this.isDirty = false;
		this.baseAddress = -1;
		this.value = -1;
		this.words = new int[4];
	}
}
