package example.command.wikipedia;

/* Client class */
public class PressSwitch {

	public static void main(String[] args) {
		Light lamp = new Light();
		Command switchUp = new FlipUpCommand(lamp);
		Command switchDown = new FlipDownCommand(lamp);
	 
		Switch s = new Switch();
		
		s.storeAndExecute(switchUp);
		System.out.println("#######");
		s.storeAndExecute(switchDown);

	}

}
