package example.memento;

public class Originator {
	private String name = "generic car";
	private String color;
	
	public Originator() {
	}
	
	public Originator(String name, String color) {
		this.name = name; this.color = color;
	}
	
	public void println() {
		System.out.println(this.toString());
	}
	
	public void repaint(String color) {
		this.color = color;
		System.out.println(this.name+" got a new paintjob! Now it is "+this.color+".");
	}
	
	public String toString() {
		return this.name+" is a "+this.color+" car.";
	}
	
	/* needed for Memento */
	
	public Memento createMemento() {
		return new Memento(color);
	}
	
	public void loadMemento(Memento memento) {
		this.color = memento.getSavedState();
	}

	public static class Memento {
		private final String state;
		
		private Memento(String state) {
			this.state = state;  
		}
		
		private String getSavedState() {
			return this.state;
		}
	}
}
