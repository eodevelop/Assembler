package module;

import java.util.List;

public class ParserModule {
	private List<String> lines;
	private String currentLine;
	private CodeModule codeModule = new CodeModule();
	
	public ParserModule(List<String> lines) {
		this.lines = lines;
	}

	public String assemble() {
		int i = 0;
		String result = "";
		SymbolTableModule symbolTableModule = new SymbolTableModule();
		
		for (String line : this.lines) {
			currentLine = line.replaceAll(" ","");

			String commandType = commandType(currentLine);
			String symbol = "";
			
			switch (commandType) {
			case "A_COMMAND":
				i++;
				break;
			case "L_COMMAND":
				symbol = symbol();
				symbolTableModule.addEntry(symbol, i);
				break;
			case "C_COMMAND":
				i++;
				break;
			}
		}
		
		for (String line : this.lines) {
			currentLine = line.replaceAll(" ","");
			currentLine = currentLine.split("//")[0];
			
			String commandType = commandType(currentLine);
			String symbol = "";
			
			switch (commandType) {
			case "A_COMMAND":
				symbol = symbol();
				result += symbolTableModule.getAddress(symbol);
				result += "\n";
				break;
			case "C_COMMAND":
				String dest = codeModule.dest(dest());
				String jump = codeModule.jump(jump());
				String comp =  codeModule.comp(comp());
				result += "111" + comp + dest + jump;
				result += "\n";
				break;
			case "COMMENT":
				break;
			}
		}
		
		return result;
	}

	private String commandType(String command) {
		if(command.length() == 0) {
			return "";
		}
		
		if (command.length() > 1 && "//".equals(command.substring(0, 2))) {
			return "COMMENT";
		}

		switch (command.charAt(0)) {
		case '(':
			return "L_COMMAND";
		case '@':
			return "A_COMMAND";
		default:
			return "C_COMMAND";
		}
	}

	private String symbol() {
		String xxx = currentLine.replaceAll("[@()]", "");
		return xxx;
	}

	private String dest() {
		if (this.currentLine.contains("=")) {
			String returnValue = this.currentLine.split("=")[0];
			this.currentLine = this.currentLine.split("=")[1];
			return returnValue;
		}

		return "";
	}

	private String jump() {
		if (this.currentLine.contains(";")) {
			String returnValue = this.currentLine.split(";")[1];
			this.currentLine = this.currentLine.split(";")[0];
			return returnValue;
		}

		return "";
	}
	
	private String comp() {
		return this.currentLine;
	}
}
