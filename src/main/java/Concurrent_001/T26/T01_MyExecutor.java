package Concurrent_001.T26;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor {
	public static void main(String[] args) {
		new T01_MyExecutor().execute(() -> System.out.println("Hello executor!"));
	}

	@Override
	public void execute(Runnable command) {
		System.out.println("This's execute !");
		command.run();
	}

}
