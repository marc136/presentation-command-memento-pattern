package example.command.wikipedia;

import java.util.ArrayList;
import java.util.List;

/* Invoker class */
public class Switch {
	private List<Command> history = new ArrayList<Command>();
	
	public Switch() {
	}
	
	public void storeAndExecute(Command cmd) {
		this.history.add(cmd);
		cmd.execute();
	}	
}


