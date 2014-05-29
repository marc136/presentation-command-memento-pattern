package prettysource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Sourcecode {
	private List<String> codeLines;
	private String indent = "\t";
	private String type = "java";

	public Sourcecode(String filename, String type) {
		String file = filename+"."+type;
		String msg = "file '"+file+"' was loaded.";
		
		if (!loadFromFile(file)) {
			msg = "loading file |"+file+"' failed.";
		}
		
		this.type = type;
		log(msg);
	}
	
	

	/* Memento functions */
	
	public Memento createMemento() {
		return new Memento(indent, codeLines);
	}
	
	public void loadMemento(Memento mem) {
		this.indent = mem.getIndent();
		this.codeLines = mem.getCodeLines();
	}
	
	/* Memento class */
	
	public static class Memento {
		private final List<String> codeLines;
		private final String indent;
		
		private Memento(String indent, List<String> codeLines) {
			this.indent = indent;
			this.codeLines = codeLines;
		}

		public List<String> getCodeLines() {
			return codeLines;
		}

		public String getIndent() {
			return indent;
		}
		
	}

	
	/* just useful functions */
	
	public boolean loadFromFile(String filename) {
		try {
			Path filepath = new File(filename).toPath();
			codeLines = Files.readAllLines(filepath, Charset.defaultCharset());
		} catch (IOException e) {
			System.out.println("Error: could not find file '"+filename+"' in current folder"); 
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void log(String msg) {
		System.out.println("- IDE - "+msg);
	}
	
	public void println() {
		for (String line : codeLines) {
			System.out.println(line);
		}
		System.out.println("indentation: '"+indent+"'");
	}

	public void replaceAll(String regex, String replacement) {
		List<String> newLines = new ArrayList<String>();
		
		for (String line : codeLines) {
			newLines.add(line.replaceAll(regex, replacement));
		}
		
		codeLines = newLines;
	}
	
	public boolean saveToFile() { return saveToFile("lazy."+type); }
	public boolean saveToFile(String filename) {
		try {
			File file = new File(filename);
			if (file.exists()) { file.delete(); }
			Files.write(file.toPath(), codeLines, Charset.defaultCharset(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			System.out.println("Error: saving to file '"+filename+"' failed.");
			//e.printStackTrace();
			return false;
		}
		return true;
	}


	/* Auto generated Getters and Setters */

	public List<String> getCodeLines() {
		return codeLines;
	}

	public void setCodeLines(List<String> codeLines) {
		this.codeLines = codeLines;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(String indent) {
		this.indent = indent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
