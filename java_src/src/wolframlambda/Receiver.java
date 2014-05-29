package wolframlambda;

import java.util.HashMap;

public class Receiver {
	private HashMap<String, Number> solutions = new HashMap<String, Number>();
	
	public Receiver() {
	}
	
	public void add(String key, Number value) {
		solutions.put(key, value);

		this.println();
	}
	
	public String toString() {
		String result = "";

		for (String key : solutions.keySet()) {
			result += String.format(" "+key+" = "+solutions.get(key)+"\n");
		}
		
		return result;
	}
	
	public void println() {
		System.out.println("\n---\nWolframLambda solved so far the following "+solutions.size()+" equations:");
		System.out.print(this.toString());
	}
}
