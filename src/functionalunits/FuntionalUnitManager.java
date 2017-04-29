package functionalunits;

import java.util.LinkedList;
import java.util.Queue;

import common.AppConfig;
import common.constants.CommonConstants;
import test.Test;

public class FuntionalUnitManager {
	public static Queue<FpAdder> fpAdderAvailable = new LinkedList<>();
	public static Queue<FpMultiplier> fpMultiplierAvailable = new LinkedList<>();
	public static Queue<FpDivider> fpDividerAvailable = new LinkedList<>();
	public static Queue<IntegerUnit> integerUnitAvailable = new LinkedList<>();
	public static Queue<LoadStoreUnit> loadStoreUnitAvailable = new LinkedList<>();

	public static void initFuntionalUnits() {
		for(int i=0; i<AppConfig.appConfig.getNumFpAdderUnits(); i++)
			fpAdderAvailable.add(new FpAdder());
		for(int i=0; i<AppConfig.appConfig.getNumFpMultiplierUnits(); i++)
			fpMultiplierAvailable.add(new FpMultiplier());
		for(int i=0; i<AppConfig.appConfig.getNumFpDividerUnits(); i++)
			fpDividerAvailable.add(new FpDivider());
		
		integerUnitAvailable.add(new IntegerUnit());
		loadStoreUnitAvailable.add(new LoadStoreUnit());
	}
	
	public static FunctionalUnit getFunctionalUnit(String type){
		FunctionalUnit f = null;
		if(type == CommonConstants.FPADDER && !fpAdderAvailable.isEmpty()){
			f = fpAdderAvailable.poll();
		} else if(type == CommonConstants.FPMULTIPLIER && !fpMultiplierAvailable.isEmpty()){
			f = fpMultiplierAvailable.poll();
		}else if(type == CommonConstants.FPDIVIDER && !fpDividerAvailable.isEmpty()){
			f = fpDividerAvailable.poll();
		} else if(type == CommonConstants.LOADSTORE && !loadStoreUnitAvailable.isEmpty()){
			f = loadStoreUnitAvailable.poll();
		} else if(type == CommonConstants.INTEGER && !integerUnitAvailable.isEmpty()) {
			f = integerUnitAvailable.poll(); 
		}
		return f;
	}
	
	public static FunctionalUnit putFunctionalUnit(String type){ // function to add units back to pool
		FunctionalUnit f = null;
		// check if the max pool size is reached to protect adding more units than allowed
		if(type == CommonConstants.FPADDER && fpAdderAvailable.size() < AppConfig.appConfig.getNumFpAdderUnits()){
			fpAdderAvailable.offer(new FpAdder());
		} else if(type == CommonConstants.FPMULTIPLIER && fpMultiplierAvailable.size() < AppConfig.appConfig.getNumFpMultiplierCycleCount()){
			fpMultiplierAvailable.offer(new FpMultiplier());
		}else if(type == CommonConstants.FPDIVIDER && fpDividerAvailable.size() < AppConfig.appConfig.getNumFpDividerCycleCount()){
			fpDividerAvailable.offer(new FpDivider());
		} else if(type == CommonConstants.LOADSTORE && loadStoreUnitAvailable.size() < 1){
			loadStoreUnitAvailable.offer(new LoadStoreUnit());
		} else if(type == CommonConstants.INTEGER && integerUnitAvailable.size() < 1) {
			integerUnitAvailable.offer(new IntegerUnit()); 
		}
		return f;
	}
	
	
}