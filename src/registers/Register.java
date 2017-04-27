package registers;

public class Register {
	static double[] R = new double[32];
	static double[] F = new double[32];
	static boolean[] RReadStatus = new boolean[32];
	static boolean[] FReadStatus = new boolean[32];
	static boolean[] RWriteStatus = new boolean[32];
	static boolean[] FWriteStatus = new boolean[32];
	
	public static double getRegister(String name){
		if(name.charAt(0) == 'R')
			return R[Integer.parseInt(name.substring(1))];
		else
			return F[Integer.parseInt(name.substring(1))];
	}
	
	public static void setRegister(String name, double value){
		if(name.charAt(0) == 'R')
			R[Integer.parseInt(name.substring(1))] = value;
		else
			F[Integer.parseInt(name.substring(1))] = value;
	}
	
	public static boolean getRegReadStatus(String name){
		if(name.charAt(0) == 'R')
			return RReadStatus[Integer.parseInt(name.substring(1))];
		else
			return FReadStatus[Integer.parseInt(name.substring(1))];
	}
	
	public static void setRegReadStatus(String name){
		if(name.charAt(0) == 'R')
			RReadStatus[Integer.parseInt(name.substring(1))] = true;
		else
			FReadStatus[Integer.parseInt(name.substring(1))] = true;
	}
	
	public static boolean getRegWriteStatus(String name){
		if(name.charAt(0) == 'R')
			return RWriteStatus[Integer.parseInt(name.substring(1))];
		else
			return FWriteStatus[Integer.parseInt(name.substring(1))];
	}
	
	public static void setRegWriteStatus(String name){
		if(name.charAt(0) == 'R')
			RWriteStatus[Integer.parseInt(name.substring(1))] = true;
		else
			FWriteStatus[Integer.parseInt(name.substring(1))] = true;
	}
}
