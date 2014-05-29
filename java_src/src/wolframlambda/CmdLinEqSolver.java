package wolframlambda;

public class CmdLinEqSolver implements Command {
	private Receiver receiver;
	private String equation;

	public CmdLinEqSolver(Receiver receiver, String equation) {
		this.receiver = receiver;
		this.equation = equation;
	}

	@Override
	public void execute() {
		double solution = 5;
		receiver.add(equation, solution);
	}

}
