package common;

import common.constants.CommonConstants;
import namedinstrucion.LW;

public class InstructionLoader {

	public static Instruction getLoadedInstruction(String opcode, String[] operands) {
		// TODO Auto-generated method stub
		Instruction inst = null;
		String sourceReg1;
		String sourceReg2;
		String destReg;
		int offset;
		int imm;
		
		switch(opcode){
		
		case CommonConstants.LW:
			destReg = operands[0];
			offset = Integer.parseInt(operands[1].substring(0,  operands[1].indexOf('(')));
			sourceReg1 = operands[1].substring(operands[1].indexOf('(') + 1,  operands[1].indexOf(')'));
			inst = new LW(destReg, sourceReg1, offset);
			break;
		case CommonConstants.SW:break;
		case CommonConstants.LD:break;
		case CommonConstants.SD:break;
		case CommonConstants.DADD:break;
		case CommonConstants.DADDI:break;
		case CommonConstants.DSUB:break;
		case CommonConstants.DSUBI:break;
		case CommonConstants.AND:break;
		case CommonConstants.ANDI:break;
		case CommonConstants.OR:break;
		case CommonConstants.ORI:break;
		case CommonConstants.LI:break;
		case CommonConstants.LUI:break;
		case CommonConstants.ADDD:break;
		case CommonConstants.MULTD:break;
		case CommonConstants.DIVD:break;
		case CommonConstants.SUBD:break;
		case CommonConstants.JUMP:break;
		case CommonConstants.BEQ:break;
		case CommonConstants.BNE:break;
		case CommonConstants.HLT:break;	
		}
		
		return inst;
	}
	
}
