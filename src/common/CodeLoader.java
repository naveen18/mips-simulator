package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import common.constants.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class CodeLoader {
	public static TreeMap<Integer, Instruction> instMap = new TreeMap();
	public static HashMap<String, Integer> lablMap = new HashMap<>();

	public static void loadCode(String filename) throws Exception {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			int count = 0;
			while (line != null && line.length() != 0) {				
				Instruction instruc = createInstructionObj(line, count);
				instMap.put(count, instruc);
				line = br.readLine();
				count++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Instruction createInstructionObj(String instruc, int count) throws Exception {
		
		instruc = instruc.toUpperCase();
		Instruction inst1 = null;
		String[] split1 = instruc.split(":");
		String opcode = null;
		int shift = 0;
		if (split1.length > 1) {
			lablMap.put(split1[0], count);
			//inst.setLabel(split1[0]);
			shift = 1;
		}
		String[] split2 = split1[shift].trim().split(" ", 2);
		opcode = split2[0].trim();
		if (split2.length >= 2) {
			String[] operands = split2[1].split(" ");
			for (int i = 0; i < operands.length; i++)
				operands[i] = operands[i].replaceAll(",", "");
			inst1 = InstructionLoader.getLoadedInstruction(opcode, operands);
		} else {
			inst1 = InstructionLoader.getLoadedInstruction(opcode, null);
		}

		return inst1;

	}
}
