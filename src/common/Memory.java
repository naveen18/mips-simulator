package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.StringBuilder;

public class Memory {
	private static byte[] memory = null;

	public static void readMemory(String filename) throws IOException {
		if (memory == null) {
			memory = new byte[384];
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			int  i = 0;
			byte b = 0;
			while(line != null && line != ""){
				for (int j = 0; j <= line.length() - 8; j += 8) {
					String s = line.substring(j, j + 8);
					int num = Integer.parseInt(s, 2);
					b = (byte)num;
					//each line has 4 bytes, 100 base address for data and j/8 offset for byte
					memory[256 + i*4 + j/8] = b;  
				}
				i++;
				line = br.readLine();
			}
			br.close();
		}
	}

	public static byte getWord(int address) {
		return memory[address];
	}
}
