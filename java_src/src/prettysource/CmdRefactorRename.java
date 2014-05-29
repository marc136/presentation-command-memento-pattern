package prettysource;

public class CmdRefactorRename implements Command {
	private Sourcecode receiver;
	private String oldName;
	private String newName;
	private Sourcecode.Memento memento;

	public CmdRefactorRename(Sourcecode receiver, String oldName, String newName) {
		this.receiver = receiver;
		this.oldName = oldName;
		this.newName = newName;
	}

	@Override
	public void execute() {
		memento = receiver.createMemento();
		receiver.replaceAll(oldName, newName);
	}

	@Override
	public void undo() {
		receiver.loadMemento(memento);
		//memento = null;
	}

	public String toString() {
		return "rename '"+oldName+"' to '"+newName+"'";
	}
}
