package module;

import java.util.List;

public class ParserModule {
	private List<String> lines;
	
	public ParserModule(List<String> lines) {
		this.lines = lines;
	}

	public void Initializer() {
	
		for(String line : this.lines) {
			String command = line.trim();
			
			String commandType = commandType(command);
			
			switch(commandType) {
			case "A_COMMAND", "L_COMMAND" :
				symbol();
				break;
			case "C_COMMAND" :
				break;
			case "COMMENT" :
				break;
			case "ERROR" :
				System.out.println("ERROR");
				return;
			}
			
		}
	}
	
	private String commandType(String command) {
		if(command.length() > 1 && "//".equals(command.substring(0, 2))) {
			return "COMMENT";
		}
		
		switch(command.charAt(0)) {
		case '(' :
			return "L_COMMAND";
		case '@' :
			return "A_COMMAND";
		case 'D', 'A', 'M':
			return "C_COMMAND";
		default :
			return "ERROR";
		}
	}

	private String symbol() {

		return "";
	}

	private String dest() {

		return "";
	}

	private String comp() {

		return "";
	}

	private String jump() {

		return "";
	}
}
