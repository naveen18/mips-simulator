package common;

import common.constants.CommonConstants;
import namedinstrucion.LD;
import namedinstrucion.LI;
import namedinstrucion.LUI;
import namedinstrucion.LW;
import namedinstrucion.MULTD;
import namedinstrucion.OR;
import namedinstrucion.ORI;
import namedinstrucion.SD;
import namedinstrucion.SUBD;
import namedinstrucion.SW;
import namedinstrucion.ADDD;
import namedinstrucion.AND;
import namedinstrucion.ANDI;
import namedinstrucion.BEQ;
import namedinstrucion.BNE;
import namedinstrucion.DADD;
import namedinstrucion.DADDI;
import namedinstrucion.DIVD;
import namedinstrucion.DSUB;
import namedinstrucion.DSUBI;
import namedinstrucion.HLT;
import namedinstrucion.J;

public class InstructionLoader {

	public static Instruction getLoadedInstruction(String opcode, String[] operands) throws Exception {
		// TODO Auto-generated method stub
		Instruction inst = null;
		String reg1;
		String reg2;
		String reg3;
		int offset;
		int imm;

		switch (opcode) {

		case CommonConstants.LW: // add logic to handle the case without () i.e
									// memory offer
			reg1 = operands[0];
			offset = Integer.parseInt(operands[1].substring(0, operands[1].indexOf('(')));
			reg2 = operands[1].substring(operands[1].indexOf('(') + 1, operands[1].indexOf(')'));
			inst = new LW(reg1, reg2, offset, opcode, CommonConstants.LOADSTORE);
			break;
		case CommonConstants.SW: // add logic to handle the case without () i.e
									// memory offer
			reg1 = operands[0];
			offset = Integer.parseInt(operands[1].substring(0, operands[1].indexOf('(')));
			reg2 = operands[1].substring(operands[1].indexOf('(') + 1, operands[1].indexOf(')'));
			inst = new SW(reg1, reg2, offset, opcode, CommonConstants.LOADSTORE);
			break;
		case CommonConstants.LD:
			reg1 = operands[0];
			offset = Integer.parseInt(operands[1].substring(0, operands[1].indexOf('(')));
			reg2 = operands[1].substring(operands[1].indexOf('(') + 1, operands[1].indexOf(')'));
			inst = new LD(reg1, reg2, offset, opcode, CommonConstants.LOADSTORE);
			break;
		case CommonConstants.SD:
			reg1 = operands[0];
			offset = Integer.parseInt(operands[1].substring(0, operands[1].indexOf('(')));
			reg2 = operands[1].substring(operands[1].indexOf('(') + 1, operands[1].indexOf(')'));
			inst = new SD(reg1, reg2, offset, opcode, CommonConstants.LOADSTORE);
			break;
		case CommonConstants.DADD:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new DADD(reg1, reg2, reg3, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.DADDI:
			reg1 = operands[0];
			reg2 = operands[1];
			imm = Integer.parseInt(operands[2]);
			inst = new DADDI(reg1, reg2, imm, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.DSUB:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new DSUB(reg1, reg2, reg3, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.DSUBI:
			reg1 = operands[0];
			reg2 = operands[1];
			imm = Integer.parseInt(operands[2]);
			inst = new DSUBI(reg1, reg2, imm, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.AND:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new AND(reg1, reg2, reg3, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.ANDI:
			reg1 = operands[0];
			reg2 = operands[1];
			imm = Integer.parseInt(operands[2]);
			inst = new ANDI(reg1, reg2, imm, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.OR:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new OR(reg1, reg2, reg3, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.ORI:
			reg1 = operands[0];
			reg2 = operands[1];
			imm = Integer.parseInt(operands[2]);
			inst = new ORI(reg1, reg2, imm, opcode, CommonConstants.INTEGER);
		case CommonConstants.LI:
			reg1 = operands[0];
			imm = Integer.parseInt(operands[1]);
			inst = new LI(reg1, imm, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.LUI:
			reg1 = operands[0];
			imm = Integer.parseInt(operands[1]);
			inst = new LUI(reg1, imm, opcode, CommonConstants.INTEGER);
			break;
		case CommonConstants.ADDD:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new ADDD(reg1, reg2, reg3, opcode, CommonConstants.FPADDER);
			break;
		case CommonConstants.MULTD:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new MULTD(reg1, reg2, reg3, opcode, CommonConstants.FPMULTIPLIER);
			break;
		case CommonConstants.DIVD:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new DIVD(reg1, reg2, reg3, opcode, CommonConstants.FPDIVIDER);
			break;
		case CommonConstants.SUBD:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new SUBD(reg1, reg2, reg3, opcode, CommonConstants.FPADDER);
			break;
		case CommonConstants.JUMP:
			reg1 = operands[0];
			inst = new J(reg1, opcode, CommonConstants.UNKNOWN_UNIT);
			break;
		case CommonConstants.BEQ:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new BEQ(reg1, reg2, reg3, opcode, CommonConstants.UNKNOWN_UNIT);
			break;
		case CommonConstants.BNE:
			reg1 = operands[0];
			reg2 = operands[1];
			reg3 = operands[2];
			inst = new BNE(reg1, reg2, reg3, opcode, CommonConstants.UNKNOWN_UNIT);
			break;
		case CommonConstants.HLT:
			inst = new HLT(opcode, CommonConstants.UNKNOWN_UNIT);
			break;
		default:
			throw new Exception("Invalid instruction " + opcode);
		}

		return inst;
	}

}
