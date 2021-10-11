package module;

import java.util.List;

public class ParserModule {
	private List<String> lines;
	private String currentLine;
	private CodeModule codeModule = new CodeModule();
	
	public ParserModule(List<String> lines) {
		this.lines = lines;
	}

	public void Initializer() {

		for (String line : this.lines) {
			currentLine = line.replaceAll(" ","");

			String commandType = commandType(currentLine);

			switch (commandType) {
			case "A_COMMAND", "L_COMMAND":
				String symbol = symbol();
				System.out.println(symbol);
				break;
			case "C_COMMAND":
				String dest = codeModule.dest(dest());
				String jump = codeModule.jump(jump());
				String comp =  codeModule.comp(comp());
				System.out.println("111" + comp + dest + jump);
				break;
			case "COMMENT":
				break;
			}

		}
	}

	private String commandType(String command) {
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
			this.currentLine = this.currentLine.split("=")[1];
			return this.currentLine.split("=")[0];
		}

		return "";
	}

	private String jump() {
		if (this.currentLine.contains(";")) {
			this.currentLine = this.currentLine.split(";")[0];
			return this.currentLine.split("=")[1];
		}

		return "";
	}
	
	private String comp() {
		return this.currentLine;
	}
}
