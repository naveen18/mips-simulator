package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.StringBuilder;

public class Memory {
	private static byte[] memory;

	public static void readMemory(String filename) throws IOException {
		if (memory == null) {
			memory = new byte[384];
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			int i = 0;
			byte b = 0;
			while (line != null && line != "") {
				for (int j = 0; j <= line.length() - 8; j += 8) {
					String s = line.substring(j, j + 8);
					int num = Integer.parseInt(s, 2);
					b = (byte) num;
					// each line has 4 bytes, 100 base address for data and j/8
					// offset for byte
					memory[256 + i * 4 + j / 8] = b;
				}
				i++;
				line = br.readLine();
			}
			br.close();
		}
	}

	public static int getByte(int address) {
		return memory[address];
	}

	public static int getWord(int address) {
		short s = twoBytesToShort(memory[address], memory[address + 1]);
		return s;
	}

	public static double getDouble(int address) {
		short s1 = twoBytesToShort(memory[address], memory[address + 1]);
		short s2 = twoBytesToShort(memory[address + 2], memory[address + 3]);
		return (int) ((s1 << 16) | (s2 & 0xFFFF));
	}

	public static void storeByte(int address, int val) {
		memory[address] = (byte) val;
	}

	public static void storeWord(int address, int val) {
		memory[address] = (byte) ((val & 0xFF));
		memory[address + 1] = (byte) ((val >> 8) & 0xFF);
	}

	public static void storeDouble(int address, double val) {
		byte[] b = ByteBuffer.allocate(4).putDouble(val).array();
		memory[address] = b[0];
		memory[address + 1] = b[1];
		memory[address + 2] = b[2];
		memory[address + 3] = b[3];
	}

	public static short twoBytesToShort(byte b1, byte b2) {
		return (short) ((b1 << 8) | (b2 & 0xFF));
	}
}
