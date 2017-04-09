import java.io.IOException;

import common.AppConfig;
import common.Memory;

public class Test {
	public static void main(String args[]) {
		AppConfig.updateConfig("/home/naveen/Desktop/Architecture/config.txt");
		try {
			Memory.readMemory("/home/naveen/Desktop/Architecture/data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=100; i<228; i++){
			System.out.println(Memory.getWord(i));
		}	
	}
}
