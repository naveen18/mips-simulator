package pipeline;

import test.Test;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;

public class FetchStage {
	public static Map<Integer, IssueStage> isMap = new HashMap<Integer, IssueStage>();
	public boolean busy;
	public static IssueStage loadStorePipeline;
	public static IssueStage integerPipeline;
	public static ArrayList<IssueStage> fpMultPipelines;
	public static ArrayList<IssueStage> fpAddPipelines;
	public static ArrayList<IssueStage> fpDivPipelines;

	public void fetchInstruction() {
		initPipelines();
		for (Entry<Integer, Instruction> inst : CodeLoader.instMap.entrySet()) {
			Instruction ins = inst.getValue();
			System.out.println(Test.clockCycle);
//			System.out.println(inst);
			Test.clockCycle++;
			if(ins.pipelineType.equals(CommonConstants.FPADDER)){ // FP add instruction
				int i = 0;
				while(true){
					if(fpAddPipelines.get(i).busy){
						Test.clockCycle++;
						i = (i + 1)%(fpAddPipelines.size());
					} else{
						break;
					}
				}
				fpAddPipelines.get(i).issueInstruction(ins, fpAddPipelines.get(i).id);
			} else if(ins.pipelineType.equals(CommonConstants.FPMULTIPLIER)){ // FP Multiply instruction
				int i = 0;
				while(true){
					if(fpMultPipelines.get(i).busy){
						Test.clockCycle++;
						i = (i + 1)%(fpMultPipelines.size());
					} else{
						break;
					}
				}
				fpMultPipelines.get(i).issueInstruction(ins, fpMultPipelines.get(i).id);
			} else if(ins.pipelineType.equals(CommonConstants.FPDIVIDER)){ // FP Divide instruction
				int i = 0;
				while(true){
					if(fpDivPipelines.get(i).busy){
						Test.clockCycle++;
						i = (i + 1)%(fpDivPipelines.size());
					} else{
						break;
					}
				}
				fpDivPipelines.get(i).issueInstruction(ins, fpDivPipelines.get(i).id);
			} else if(ins.pipelineType.equals(CommonConstants.LOADSTORE)){ // load store type instruction
				while(loadStorePipeline.busy){
					Test.clockCycle++;
				}
				//System.out.println(isMap.get(0));
				loadStorePipeline.busy = true;
				loadStorePipeline.issueInstruction(ins, loadStorePipeline.id);
			} else { // integer type instruction
				while(integerPipeline.busy){
					Test.clockCycle++;
				}
				integerPipeline.issueInstruction(ins, integerPipeline.id);
			}
		}
	}

	public void initPipelines() {
		int k=0;
		loadStorePipeline = new IssueStage(k); k++;
		isMap.put(loadStorePipeline.id, loadStorePipeline);
		
		integerPipeline = new IssueStage(k); k++;
		isMap.put(integerPipeline.id, integerPipeline);
		
		fpMultPipelines = new ArrayList<>();	
		fpAddPipelines = new ArrayList<>();
		fpDivPipelines = new ArrayList<>();
		
		for (int i = 0; i < AppConfig.appConfig.getNumFpMultiplierUnits(); i++) {
			IssueStage is = new IssueStage(k);
			fpMultPipelines.add(is);
			isMap.put(is.id, is);
			k++;
		}
		for (int i = 0; i < AppConfig.appConfig.getNumFpAdderUnits(); i++) {
			IssueStage is = new IssueStage(k);
			fpAddPipelines.add(is);
			isMap.put(is.id, is);
			k++;
			
		}
		for (int i = 0; i < AppConfig.appConfig.getNumFpDividerUnits(); i++) {
			IssueStage is = new IssueStage(k);
			fpDivPipelines.add(is);
			isMap.put(is.id, is);
			k++;
		}
	}

}
