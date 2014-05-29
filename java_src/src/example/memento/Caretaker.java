package example.memento;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
	private List<Originator.Memento> savedStates = new ArrayList<Originator.Memento>();

	public Caretaker() {
	}

	public void saveMemento(Originator.Memento memento) {
		savedStates.add(memento);
	}
	
	public Originator.Memento getMemento(int index) {
		return savedStates.get(index);
	}
	
	public static void main(String[] args) {
		Caretaker carshop = new Caretaker();
		
		Originator myCar = new Originator("smart","red");
		myCar.println();
		carshop.saveMemento(myCar.createMemento());
		
		myCar.repaint("blue");
		carshop.saveMemento(myCar.createMemento());
		
		myCar.repaint("pink");
		carshop.saveMemento(myCar.createMemento());
		
		myCar.repaint("white");
		
		//return to a previous color
		System.out.println("\n I liked it better before..");
		myCar.loadMemento(carshop.getMemento(1));
		myCar.println();
	}

}
