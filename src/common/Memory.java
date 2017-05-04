package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.AllPermission;
import java.util.TreeMap;
import java.io.IOException;
import java.lang.StringBuilder;

public class Memory {
	private static TreeMap<Integer, Integer> memory  = new TreeMap<>();

	public static void readMemory(String filename) throws IOException {
		if (memory.isEmpty()) {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String dataLine = br.readLine();
			int dataAddress = 0x100;
			while (dataLine != null && dataLine != "") {
				dataLine = dataLine.trim();
				if(dataLine.isEmpty())
					break;
				int dataVal = Integer.parseInt(dataLine,2);
				memory.put(dataAddress, dataVal);
				dataLine = br.readLine();
				dataAddress = dataAddress + 4;
			}
			br.close();
		}
	}

	public static int getWord(int address) throws Exception {
		address = alignWordAddress(address);
		if(memory.containsKey(address)){
			return memory.get(address);
		} else{
			throw new Exception("No data found at" + address);
		}
	}
	
	public static void storeWord(int address, int val) {
		address = alignWordAddress(address);
		memory.put(address, val);
	}

	
	public static int alignWordAddress(int address){
		// since the program use word addressing, we need to align address
		int val = address - 0x100;
		val = val/4;
		int alignedAddress = 0x100 + val*4;
		return alignedAddress;
	}
	
	public static double getDouble(int address) throws Exception {
		int val1 = getWord(address);
		int val2 = getWord(address + 4);
		long res = (long)val1 << 32 | val2 & 0xFFFFFFFFL;
		return res;
	}
	
	public static void storeDouble(int address, double val) {
		long longVal = (long)val;
		int leftPart = (int)(longVal>>32);
		int rightPart = (int)longVal&0xffffffff;
		storeWord(address, leftPart);
		storeWord(address + 4, rightPart);
	}

}
