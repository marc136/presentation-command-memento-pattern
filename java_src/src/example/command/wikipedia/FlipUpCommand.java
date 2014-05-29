package example.command.wikipedia;

public class FlipUpCommand implements Command {
	private Light theLight;
	
	public FlipUpCommand(Light light) {
		this.theLight = light;
	}

	@Override
	public void execute() {
		theLight.turnOn();
	}

}
