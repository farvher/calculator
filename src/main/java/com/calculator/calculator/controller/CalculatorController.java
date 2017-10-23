package com.calculator.calculator.controller;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.calculator.calculator.enumeration.EOperations;

@Controller
public class CalculatorController {

	private final static String INDEX = "index";

	@GetMapping("/")
	public String home() {

		return INDEX;

	}

	@PostMapping("/calculate")
	public String calculate(String operation, Double result, Double var, Model m) {

		EOperations eo = EOperations.getOperation(operation);

		DoubleBinaryOperator f2 = null;
		DoubleFunction f1 = null;

		switch (eo) {
		case SUM:
			f2 = (a, b) -> a + b;
			break;
		case REST:
			f2 = (a, b) -> a - b;
			break;
		case MULT:
			f2 = (a, b) -> a * b;
			break;
		case DIVI:
			f2 = (a, b) -> a / b;
			break;
		case SEN:
			f1 = a -> a;
			break;
		case COS:
			f1 = a -> Math.sin(a);
			break;
		case TAN:
			f1 = a -> Math.tan(a);
			break;
		case LOG10:
			f1 = a -> Math.log10(a);
			break;
		case POW:
			f2 = (a, b) -> Math.pow(a, b);
			break;
		default:
			f2 = null;
			f1 = null;
			break;
		}

		m.addAttribute("result", f2 != null ? f2.applyAsDouble(result, var) : f1 != null ? f1.apply(var) : 0);

		return INDEX;

	}

	@GetMapping("/encrypt")
	public String encrypt() {

		return INDEX;

	}

	@GetMapping("/desencrypt")
	public String desEncrypt() {

		return INDEX;

	}
}
