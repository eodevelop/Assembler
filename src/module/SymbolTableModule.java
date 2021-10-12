package module;

import java.util.HashMap;
import java.util.Map;

public class SymbolTableModule {

	private Map<String, String> symbolTableMap = new HashMap<>();

	private int i = 16;

	public SymbolTableModule() {
		symbolTableMap.put("SP", "0000000000000000");
		symbolTableMap.put("LCL", "0000000000000001");
		symbolTableMap.put("ARG", "0000000000000010");
		symbolTableMap.put("THIS", "0000000000000011");
		symbolTableMap.put("THAT", "0000000000000100");
		symbolTableMap.put("SCREEN", "0100000000000000");
		symbolTableMap.put("KBD", "0110000000000000");
		symbolTableMap.put("R0", "0000000000000000");
		symbolTableMap.put("R1", "0000000000000001");
		symbolTableMap.put("R2", "0000000000000010");
		symbolTableMap.put("R3", "0000000000000011");
		symbolTableMap.put("R4", "0000000000000100");
		symbolTableMap.put("R5", "0000000000000101");
		symbolTableMap.put("R6", "0000000000000110");
		symbolTableMap.put("R7", "0000000000000111");
		symbolTableMap.put("R8", "0000000000001000");
		symbolTableMap.put("R9", "0000000000001001");
		symbolTableMap.put("R10", "0000000000001010");
		symbolTableMap.put("R11", "0000000000001011");
		symbolTableMap.put("R12", "0000000000001100");
		symbolTableMap.put("R13", "0000000000001101");
		symbolTableMap.put("R14", "0000000000001110");
		symbolTableMap.put("R15", "0000000000001111");
	}

	public void addEntry(String symbol, int address) {
		symbolTableMap.put(symbol, String.format("%016d", Long.parseLong(Integer.toBinaryString(address))));
	}

	public String getAddress(String symbol) {
		if (!symbolTableMap.containsKey(symbol)) {
			boolean isNumeric = symbol.chars().allMatch(Character::isDigit);
			if(isNumeric) {
				symbolTableMap.put(symbol, String.format("%016d", Long.parseLong(Integer.toBinaryString(Integer.parseInt(symbol)))));
			} else {
				symbolTableMap.put(symbol, String.format("%016d", Long.parseLong(Integer.toBinaryString(i))));
				i++;
			}			
		}

		return symbolTableMap.get(symbol);
	}
}
