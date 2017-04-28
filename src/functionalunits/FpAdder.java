package functionalunits;

import common.AppConfig;

public class FpAdder extends FunctionalUnit{
	public FpAdder(){
		this.executionTimeRequired = AppConfig.appConfig.getNumFpAdderCycleCount();
	}
}
