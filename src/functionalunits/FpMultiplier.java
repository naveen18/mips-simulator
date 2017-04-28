package functionalunits;

import common.AppConfig;

public class FpMultiplier extends FunctionalUnit {
	public FpMultiplier(){
		this.executionTimeRequired = AppConfig.appConfig.getNumFpMultiplierCycleCount();
	}
}
