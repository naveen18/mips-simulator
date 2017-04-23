package registers;

public class Register {
	static double[] R = new double[32];
	static double[] F = new double[32];
	
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
}
