package common.constants;

public class CommonConstants {

	// Data Transfer instructions
	public static final int DATA_START_ADD = 256;
	public static final int DATA_END_ADD = 384;

	public static final String LW = "LW";
	public static final String SW = "SW";
	public static final String LD = "L.D";
	public static final String SD = "S.D";

	// Arithmetic/Logical instructions
	public static final String DADD = "DADD";
	public static final String DADDI = "DADDI";
	public static final String DSUB = "DSUB";
	public static final String DSUBI = "DSUBI";
	public static final String AND = "AND";
	public static final String ANDI = "ANDI";
	public static final String OR = "OR";
	public static final String ORI = "ORI";
	public static final String LI = "LI";
	public static final String LUI = "LUI";
	public static final String ADDD = "ADD.D";
	public static final String MULTD = "MUL.D";
	public static final String DIVD = "DIV.D";
	public static final String SUBD = "SUB.D";

	// Control Instructions
	public static final String JUMP = "J";
	public static final String BEQ = "BEQ";
	public static final String BNE = "BNE";

	// Special Purpose Instructions
	public static final String HLT = "HLT";

	// Unit Names
	public static final String FPADDER = "FP adder";
	public static final String FPMULTIPLIER = "FP Multiplier";
	public static final String FPDIVIDER = "FP divider";
	public static final String ICACHE = "I-Cache";

	public static final String INTEGER = "Integer type pipleline";
	public static final String LOADSTORE = "Load Store pipleline";
	public static final String UNKNOWN_UNIT = "Unknown pipleline";

	public static final String DCACHE = "D-Cache";

	public static final String R1 = "R1";
	public static final String R2 = "R2";
	public static final String R3 = "R3";
	public static final String R4 = "R4";
	public static final String R5 = "R5";
	public static final String R6 = "R6";
	public static final String R7 = "R7";
	public static final String R8 = "R8";
	public static final String R9 = "R9";
	public static final String R10 = "R10";
	public static final String R11 = "R11";
	public static final String R12 = "R12";
	public static final String R13 = "R13";
	public static final String R14 = "R14";
	public static final String R15 = "R15";
	public static final String R16 = "R16";
	public static final String R17 = "R17";
	public static final String R18 = "R18";
	public static final String R19 = "R19";
	public static final String R20 = "R20";
	public static final String R21 = "R21";
	public static final String R22 = "R22";
	public static final String R23 = "R23";
	public static final String R24 = "R24";
	public static final String R25 = "R25";
	public static final String R26 = "R26";
	public static final String R27 = "R27";
	public static final String R28 = "R28";
	public static final String R29 = "R29";
	public static final String R30 = "R20";
	public static final String R31 = "R19";
	public static final String R32 = "R32";

	public static final String F1 = "F1";
	public static final String F2 = "F2";
	public static final String F3 = "F3";
	public static final String F4 = "F4";
	public static final String F5 = "F5";
	public static final String F6 = "F6";
	public static final String F7 = "F7";
	public static final String F8 = "F8";
	public static final String F9 = "F9";
	public static final String F10 = "F10";
	public static final String F11 = "F11";
	public static final String F12 = "F12";
	public static final String F13 = "F13";
	public static final String F14 = "F14";
	public static final String F15 = "F15";
	public static final String F16 = "F16";
	public static final String F17 = "F17";
	public static final String F18 = "F18";
	public static final String F19 = "F19";
	public static final String F20 = "F20";
	public static final String F21 = "F21";
	public static final String F22 = "F22";
	public static final String F23 = "F23";
	public static final String F24 = "F24";
	public static final String F25 = "F25";
	public static final String F26 = "F26";
	public static final String F27 = "F27";
	public static final String F28 = "F28";
	public static final String F29 = "F29";
	public static final String F30 = "F20";
	public static final String F31 = "F19";
	public static final String F32 = "F32";
	public static final int cacheMissPenalty = 3;
	public static final int FETCH_COLUMN = 0;
	public static final int ISSUE_COLUMN = 1;
	public static final int DECODE_COLUMN = 2;
	public static final int EXECUTE_COLUMN = 3;
	public static final int WRITEBACK_COLUMN = 4;
	public static final int RAW_COLUMN = 5;
	public static final int WAW_COLUMN = 6;
	public static final int STRUCT_COLUMN = 7;

}
