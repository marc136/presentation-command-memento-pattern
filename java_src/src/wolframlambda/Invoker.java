package wolframlambda;

public class Invoker {
	// this might be a thread pool
	
	public Invoker() {
	}
	
	public void add(Command cmd) {
		cmd.execute();
	}

}
