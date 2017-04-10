package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import common.constants.*;
import java.io.IOException;

public class CodeBytesLoader {
	public static void loadCodeBytes(String filename) throws Exception {
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();
			while (line != null || line.length() != 0) {
				byte[] code = getEncodedInstruction(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static byte[] getEncodedInstruction(String instruc) throws Exception {
		byte[] code = new byte[32];
		String label = null;
		String[] split1 = instruc.split(":");
		int shift = 0;
		if(split1.length > 1){
			label = split1[0];
			shift = 1;
		}
	
		String[] split2 = split1[shift].split(" ", 2);
		String instrucType = split2[0].trim();
		
		// rest of the instruc is in split2[1]
		int opcode = AppConfig.getOpcode(instrucType);
		if(opcode == -1) throw new Exception("invalid instruction");
		// based on opcode create method to encode the instruction of that type
		// ex Immidiate, Artithmatic, etc.
		
		
		return code;
	}


}
