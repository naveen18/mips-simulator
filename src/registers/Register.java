package registers;

import java.util.regex.Pattern;

public class Register {
	static double[] R = new double[32];
	static double[] F = new double[32];
	static boolean[] RWriteStatus = new boolean[32];
	static boolean[] FWriteStatus = new boolean[32];
	static int[] ownerOfRegR = new int[32]; // arrays to store the instruction who has lock i=on corresponding register 
	static int[] ownerOfRegF = new int[32];
	
	public static int getOwnerOfReg(String name) {
		if (name.charAt(0) == 'R')
			return ownerOfRegR[Integer.parseInt(name.substring(1))];
		else
			return ownerOfRegF[Integer.parseInt(name.substring(1))];
	}

	public static void setOwnerOfReg(String name, int instIndex) {
		if (name.charAt(0) == 'R')
			ownerOfRegR[Integer.parseInt(name.substring(1))] = instIndex;
		else
			ownerOfRegF[Integer.parseInt(name.substring(1))] = instIndex;
	}
	
	public static double getRegister(String name) {

		if (name.charAt(0) == 'R')
			return R[Integer.parseInt(name.substring(1))];
		else
			return F[Integer.parseInt(name.substring(1))];
	}

	public static void setRegister(String name, double value) {
		if (name.charAt(0) == 'R')
			R[Integer.parseInt(name.substring(1))] = value;
		else
			F[Integer.parseInt(name.substring(1))] = value;
	}

	public static boolean getRegWriteStatus(String name) {
		if (name.charAt(0) == 'R')
			return RWriteStatus[Integer.parseInt(name.substring(1))];
		else
			return FWriteStatus[Integer.parseInt(name.substring(1))];
	}

	public static void setRegWriteStatus(String name) {
		if (!isValidRegName(name))
			return;
		if (name.charAt(0) == 'R')
			RWriteStatus[Integer.parseInt(name.substring(1))] = true;
		else
			FWriteStatus[Integer.parseInt(name.substring(1))] = true;
	}

	public static void unsetRegWriteStatus(String name) {
		if (!isValidRegName(name))
			return;
		if (name.charAt(0) == 'R')
			RWriteStatus[Integer.parseInt(name.substring(1))] = false;
		else
			FWriteStatus[Integer.parseInt(name.substring(1))] = false;
	}

	public static boolean isValidRegName(String destReg) {
		// TODO Auto-generated method stub
		if (destReg == null || destReg.length() <= 1)
			return false;

		char regType = destReg.charAt(0);
		if (regType != 'R' && regType != 'F') {
			return false;
		}
		int regIndex = Integer.parseInt(destReg.substring(1));
		if (regIndex < 1 || regIndex > 32) {
			return false;
		}
		return true;
	}

}
