import java.io.IOException;

import common.AppConfig;
import common.Memory;
import common.constants.CommonConstants;

public class Test {
	public static void main(String args[]) {
		AppConfig.updateConfig("/home/naveen/Desktop/Architecture/config.txt");
		try {
			Memory.readMemory("/home/naveen/Desktop/Architecture/data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = CommonConstants.DATA_START_ADD; i < CommonConstants.DATA_END_ADD; i++) {
			System.out.println(Memory.getWord(i));
		}

	}
}
