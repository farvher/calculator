package com.calculator.calculator.enumeration;

import java.util.function.Function;

public enum EOperations {

	SUM("SUMA"), REST("RESTA"), MULT("MULTIPLICACION"), DIVI("DIVISION"), SEN("SENO"), COS("COSENO"), TAN(
			"TANGENTE"), SQRT("RAIZ"), POW("POTENCIA"), LOG10("LOGARITMO"), CE("RESET"), DECIMAL(
					"DECIMAL"), BINARIO("BINARIO"), OCTAL("OCTAL"), HEXADECIMAL("HEXADECIMAL"), BCD("BCD");

	private String name;

	private EOperations(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static EOperations getOperation(String op) {
		for (EOperations e : EOperations.values()) {
			if (e.getName().equalsIgnoreCase(op)) {
				return e;
			}
		}
		return null;
	}
}
