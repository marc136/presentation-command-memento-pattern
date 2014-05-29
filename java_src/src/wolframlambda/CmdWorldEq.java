package wolframlambda;

public class CmdWorldEq implements Command {
	private Receiver receiver;
	private String question;

	public CmdWorldEq(Receiver receiver, String question) {
		this.receiver = receiver;
		this.question = question;
	}

	@Override
	public void execute() {
		// calculate for a very long time
		this.receiver.add(this.question, 42);
	}

}
