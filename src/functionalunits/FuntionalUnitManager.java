package functionalunits;

import java.util.LinkedList;
import java.util.Queue;

import common.AppConfig;
import common.constants.CommonConstants;

public class FuntionalUnitManager {
	public static Queue<FpAdder> fpAdderAvailable = new LinkedList<>();
	public static Queue<FpMultiplier> fpMultiplierAvailable = new LinkedList<>();
	public static Queue<FpDivider> fpDividerAvailable = new LinkedList<>();
	public static Queue<IntegerUnit> integerUnitAvailable = new LinkedList<>();
	public static Queue<LoadStoreUnit> loadStoreUnitAvailable = new LinkedList<>();

	public static void initFuntionalUnits() {
		for(int i=0; i<AppConfig.appConfig.getNumFpAdderUnits(); i++)
			fpAdderAvailable.add(new FpAdder());
		for(int i=0; i<AppConfig.appConfig.getNumFpMultiplierCycleCount(); i++)
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
	
	
}