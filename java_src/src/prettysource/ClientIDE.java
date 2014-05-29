package prettysource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ClientIDE {
	private Sourcecode sourcecode;
	private HashMap<String, Command> menu = new HashMap<String, Command>();
	private UndoMachine invoker = new UndoMachine();

	public ClientIDE() {
		// load testcode.java from current folder
		sourcecode = new Sourcecode("testcode","java");
		
		menu.put("change indentation to '-+'", new CmdIndentation(sourcecode, "-+"));
		menu.put("rename class Invoker to Invokerclass", new CmdRefactorRename(sourcecode, "Invoker", "InvokerClass"));
		menu.put("rename class CmdWorkdEq to Weltfunktion", new CmdRefactorRename(sourcecode, "CmdWorldEq", "Weltfunktion"));
		menu.put("rename class Command to Kommando", new CmdRefactorRename(sourcecode, "Command", "Kommando"));
		menu.put("remove indentation", new CmdIndentation(sourcecode, ""));
	}
	
	public void run(Command cmd) {
		invoker.execute(cmd);
		//sourcecode.println();
		sourcecode.saveToFile();
	}
	public void run(int index) { 
		run(menu.get(menu.keySet().toArray()[index]));
	}
	public void run(String key) {
		System.out.println(key);
		run(menu.get(key));
	}	
	
	public static void main(String[] args) {
		
		ClientIDE client = new ClientIDE();
		
		//start Read Eval Print Loop
		client.repl();
	}

	
	
	
	/* REPL functions */
	
	public void repl() {
		Scanner reader = new Scanner(System.in);
		
		while (true) {
			System.out.println("What do you want to do?\n"+
			"c: run command | u: undo command | r: redo command | q: quit");
			System.out.print("-----------\ninput: ");
			
			switch (reader.next()) {
			case "c":
				repl_command(reader); break;
			case "u":
				repl_undo(reader); break;
			case "r":
				repl_redo(reader); break;
			case "q":
				return;
			}		
		}
	}
	
	public void repl_command(Scanner reader) {
		Object[] commands = menu.keySet().toArray();
		
		System.out.println("available commands:");
		for (int i=0; i<commands.length; i++) {
			System.out.println(" "+i+": "+commands[i]);
		}
		
		System.out.print("input: ");
		int input = reader.nextInt();
		if (input>=0 && input < commands.length) {
			run((String)commands[input]);
		} else {
			System.out.println("Error: This command does not exist.");
		}
	}
	
	public void repl_redo(Scanner reader) {
		Object[] commands = invoker.getUndone().toArray();
		
		System.out.println("redo which command?");
		for (int i=0; i<commands.length; i++) {
			System.out.println(" "+i+": "+commands[i]);
		}
		
		System.out.print("input: ");
		int input = reader.nextInt();
		if (input>=0 && input < commands.length) {
			invoker.redo(input);
			sourcecode.saveToFile();
		} else {
			System.out.println("Error: This command does not exist.");
		}
	}
	
	public void repl_undo(Scanner reader) {
		Object[] commands = invoker.getHistory().toArray();
		
		System.out.println("undo which command?");
		for (int i=0; i<commands.length; i++) {
			System.out.println(" "+i+": "+commands[i]);
		}
		
		System.out.print("input: ");
		int input = reader.nextInt();
		if (input>=0 && input < commands.length) {
			invoker.undo(input);
			sourcecode.saveToFile();
		} else {
			System.out.println("Error: This command does not exist.");
		}
	}
}
