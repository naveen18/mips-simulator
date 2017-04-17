package pipeline;

public class ExecuteStage {
	private static ExecuteStage exStage = null;

	public static ExecuteStage getExstage() {
		if (exStage == null)
			exStage = new ExecuteStage();
		return exStage;
	}
}
