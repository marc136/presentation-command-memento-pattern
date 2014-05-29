package wolframlambda;

public class CmdPower implements Command {
	private Receiver receiver;
	private int base;
	private int exponent;

	public CmdPower(Receiver receiver, int base, int exponent) {
		this.receiver = receiver;
		this.base = base;
		this.exponent = exponent;
	}

	@Override
	public void execute() {
		String equation = String.format("%d^%d", this.base, this.exponent);
		long solution = (long) Math.pow(this.base, this.exponent);
		receiver.add(equation, solution);
	}

}
