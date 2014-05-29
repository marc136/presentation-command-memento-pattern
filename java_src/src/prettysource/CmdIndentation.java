package prettysource;

public class CmdIndentation implements Command {
	private String indent;
	private Sourcecode receiver;
	private Sourcecode.Memento memento;

	public CmdIndentation(Sourcecode receiver, String indent) {
		this.receiver = receiver;
		this.indent = indent;
	}

	@Override
	public void execute() {
		memento = receiver.createMemento();
		receiver.replaceAll(receiver.getIndent(), indent);
		receiver.setIndent(indent);
	}

	@Override
	public void undo() {
		receiver.loadMemento(memento);
		//memento = null;
	}
	
	public String toString() {
		return "change indentation to '"+indent+"'";
	}

}