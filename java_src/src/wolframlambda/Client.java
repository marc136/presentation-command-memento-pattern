package wolframlambda;

public class Client {

	public static void main(String[] args) {
		//create Invoker
		Invoker threadpool = new Invoker();
		//create an Object to hold Solutions
		Receiver solvedEquations = new Receiver();
		
		//create equation and pass it to Invoker
		Command eq1 = new CmdWorldEq(solvedEquations,"Question about the universe and all else");
		threadpool.add(eq1);
		
		//create equation and pass it to Invoker
		Command eq2 = new CmdPower(solvedEquations, 2, 12);
		threadpool.add(eq2);
		
		//create equation and pass it to Invoker
		Command eq3 = new CmdWorldEq(solvedEquations,"How much is the fish?");
		threadpool.add(eq3);
		
		//create equation and pass it to Invoker
		Command eq4 = new CmdLinEqSolver(solvedEquations,"17-3a = 2 -> a");
		threadpool.add(eq4);
	}

}
