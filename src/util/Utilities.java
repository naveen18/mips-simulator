package util;

public class Utilities {
	public static int getMask(int num) {
		int count = 0;
		int myNum = 0;
		while (myNum < num) {
			myNum = (int) Math.pow(2, count);
			count++;
		}
		return myNum - 1;
	}
}
