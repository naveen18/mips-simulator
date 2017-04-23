package pipeline;

import test.Test;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import common.AppConfig;
import common.CodeLoader;
import common.Instruction;
import common.constants.CommonConstants;

public class FetchStage {
	public boolean busy;
	public IssueStage loadStorePipeline;
	public IssueStage integerPipeline;
	ArrayList<IssueStage> fpMultPipelines;
	ArrayList<IssueStage> fpAddPipelines;
	ArrayList<IssueStage> fpDivPipelines;

	public void fetchInstruction() {
		initPipelines();
		for (Entry<Integer, Instruction> inst : CodeLoader.instMap.entrySet()) {
			Instruction ins = inst.getValue();
			Test.clockCycle++;
			if(ins.pipelineType.equals(CommonConstants.FPADDER)){ // FP add instruction
				int i = 0;
				while(true){
					if(!fpAddPipelines.get(i).busy){
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
					if(!fpMultPipelines.get(i).busy){
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
					if(!fpDivPipelines.get(i).busy){
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
		loadStorePipeline = new IssueStage(0);
		integerPipeline = new IssueStage(0);
		fpMultPipelines = new ArrayList<>();
		fpAddPipelines = new ArrayList<>();
		fpDivPipelines = new ArrayList<>();
		for (int i = 0; i < AppConfig.appConfig.getNumFpMultiplierUnits(); i++) {
			fpMultPipelines.add(new IssueStage(i));
		}
		for (int i = 0; i < AppConfig.appConfig.getNumFpAdderUnits(); i++) {
			fpAddPipelines.add(new IssueStage(i));
		}
		for (int i = 0; i < AppConfig.appConfig.getNumFpDividerUnits(); i++) {
			fpDivPipelines.add(new IssueStage(i));
		}
	}

}
