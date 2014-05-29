package example.command.wikipedia;

public class FlipDownCommand implements Command {
	private Light theLight;
	
	public FlipDownCommand(Light light) {
		this.theLight = light;
	}

	@Override
	public void execute() {
		theLight.turnOff();
	}

}
