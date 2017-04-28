package functionalunits;

import common.AppConfig;

public class FpDivider extends FunctionalUnit {
	public FpDivider(){
		this.executionTimeRequired = AppConfig.appConfig.getNumFpDividerCycleCount();
	}
}
