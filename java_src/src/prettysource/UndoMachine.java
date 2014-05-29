package prettysource;

import java.util.ArrayList;

/* both Invoker and Caretaker */ 
public class UndoMachine {
	private ArrayList<Command> history = new ArrayList<Command>();
	private ArrayList<Command> undone = new ArrayList<Command>();

	public UndoMachine() {
	}
	
	public void execute(Command cmd) {
		cmd.execute();
		history.add(cmd);
	}
	
	
	public void redo() {
		redo(undone.size()-1);		
	}
	
	public void redo(Command cmd) {
		cmd.execute();
		history.add(cmd);
		undone.remove(cmd);
	}
	
	public void redo(int index) {
		redo(undone.get(index));
	}

	public void undo(Command cmd) {
		int index = history.indexOf(cmd);
		
		if (index == history.size()-1) {
			// passed command was the last command, just undo it
			cmd.undo();
			history.remove(cmd);
			undone.add(cmd);
		} else {
			// passed command is not the most recent command
			
			// undo all commands that were issued after the passed command
			for (int i=(history.size()-1); i>=index; i--) {
				history.get(i).undo();
			}
			
			// remove passed command from history
			history.remove(cmd);
			undone.add(cmd);
			
			// redo all commands that were issued after the passed command
			for (int i=index; i<history.size(); i++) {
				history.get(i).execute();
			}
		}
	}
	
	public void undo() {
		undo(history.size()-1);
	}
	
	public void undo(int index) {
		undo(history.get(index));
	}

	
	/* getters */
	public ArrayList<Command> getHistory() {
		return history;
	}

	public ArrayList<Command> getUndone() {
		return undone;
	}

}
