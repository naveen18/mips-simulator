package pipeline;

public class WriteBackStage {
	private static WriteBackStage wStage = null;

	public static WriteBackStage getwStage() {
		if (wStage == null)
			wStage = new WriteBackStage();
		return wStage;
	}
}
